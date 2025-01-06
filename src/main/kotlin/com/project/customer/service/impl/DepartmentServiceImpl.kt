package com.project.customer.service.impl

import com.project.customer.event.DepartmentEventPublisher
import com.project.customer.mapper.DepartmentMapper
import com.project.customer.api.dto.departmentbusinessevent.DepartmentBusinessEvent
import com.project.customer.domain.department.Department
import com.project.customer.api.dto.department.DepartmentRequest
import com.project.customer.api.dto.department.DepartmentResponse
import com.project.customer.repository.DepartmentRepository
import com.project.customer.service.DepartmentService
import org.modelmapper.ModelMapper
import org.springframework.data.domain.*
import org.springframework.stereotype.Service

@Service
class DepartmentServiceImpl(
    private val departmentRepository: DepartmentRepository,
    private val modelMapper: ModelMapper,
    private val mupStructMapper: DepartmentMapper,
    private val publisher: DepartmentEventPublisher
) : DepartmentService {
    companion object {
        private val LOGGER = org.slf4j.LoggerFactory.getLogger(DepartmentServiceImpl::class.java)
    }

    override fun getAll(page: Int, size: Int, sortField: String): Page<DepartmentResponse> {
       val pageable: Pageable = PageRequest.of(page, size, Sort.Direction.fromString(sortField))
       return departmentRepository.findAll(pageable)
           .map { modelMapper.map(it, DepartmentResponse::class.java) }
           .also { LOGGER.info("Get Entity's list, size: " + it.size) }
    }

    override fun save(departmentRequest: DepartmentRequest): DepartmentResponse {
        return departmentRepository.save(mupStructMapper.toBean(departmentRequest))
            .let { modelMapper.map(it, DepartmentResponse::class.java) }
            .also {
                it.id?.let { it1 -> createEvent("Save Some information with ID ", it1) }
                    ?.let { it2 -> publisher.publishEvent(it2) }
            }
            .also { LOGGER.info("Save Entity with id ${it.id}") }
    }

    override fun getById(id: Long): DepartmentResponse {
        return departmentRepository.findById(id)
            .map { modelMapper.map(it, DepartmentResponse::class.java) }
            .orElseThrow { RuntimeException("Department with given id not found !") }
            .also { LOGGER.info("Get Entity with id $id") }
    }

    override fun update(id: Long, departmentRequest: DepartmentRequest): DepartmentResponse {
        require(!departmentRepository.existsById(id)) { "Department Id must be existing !" }
        return modelMapper.map(
            departmentRepository.save(mupStructMapper.toBean(departmentRequest)
                .apply { this.setId(id) }), DepartmentResponse::class.java
        )
            .also { LOGGER.info("Update Entity with id $id") }
    }

    override fun delete(id: Long) {
        require(!departmentRepository.existsById(id)) { "Department Id must be existing !" }

        departmentRepository.deleteById(id)
            .also { LOGGER.info("Delete Entity with id $id") }
        publisher.publishEvent(createEvent("Delete Some information with ID", id))
    }

    private fun toPage(departments: List<Department>): Page<DepartmentResponse> {
        val result = mutableListOf<DepartmentResponse>()
        departments.map { result.add(modelMapper.map(it, DepartmentResponse::class.java)) }
        val page: PageImpl<DepartmentResponse> = PageImpl(result)
        return page
    }

    private fun createEvent(evnMessage: String, entityId: Long): DepartmentBusinessEvent {
        return DepartmentBusinessEvent(
            sourceObject = this,
            payload = "$evnMessage $entityId"
        )
    }
}