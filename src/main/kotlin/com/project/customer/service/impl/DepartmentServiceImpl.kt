package com.project.customer.service.impl

import com.project.customer.api.dto.department.DepartmentRequest
import com.project.customer.api.dto.department.DepartmentResponse
import com.project.customer.api.dto.departmentbusinessevent.DepartmentBusinessEvent
import com.project.customer.domain.department.Department
import com.project.customer.event.DepartmentEventPublisher
import com.project.customer.mapper.DepartmentMapper
import com.project.customer.repository.DepartmentRepository
import com.project.customer.service.BaseService
import org.springframework.data.domain.*
import org.springframework.stereotype.Service

@Service
class DepartmentServiceImpl(
    private val departmentRepository: DepartmentRepository,
    private val mupStructMapper: DepartmentMapper,
    private val publisher: DepartmentEventPublisher
) : BaseService<DepartmentRequest, DepartmentResponse> {
    companion object {
        private val LOGGER = org.slf4j.LoggerFactory.getLogger(DepartmentServiceImpl::class.java)
    }

    override fun save(entityCreate: DepartmentRequest): DepartmentResponse {
        return departmentRepository.save(mupStructMapper.toBean(entityCreate))
            .let { mupStructMapper.toDto(it) }
            .also {
                it.id?.let { it1 -> createEvent("Save Some information with ID ", it1) }
                    ?.let { it2 -> publisher.publishEvent(it2) }
            }
            .also { LOGGER.info("Save Entity with id ${it.id}") }
    }

    override fun update(entityId: Long, entityUpdated: DepartmentRequest): DepartmentResponse {
        isExists(entityId)

        return entityId
            .let {
                mupStructMapper.toDto(
                    departmentRepository.save(
                        mupStructMapper.toBean(entityUpdated)
                            .apply { this.setId(entityId) }
                    )
                )
            }
            .also { LOGGER.info("Update Entity with id $entityId") }
    }

    override fun findAll(page: Int, size: Int, sortField: String): Page<DepartmentResponse> {
        val pageable: Pageable = PageRequest.of(page, size, Sort.Direction.ASC, sortField)
        return departmentRepository.findAll(pageable)
            .map { mupStructMapper.toDto(it) }
            .also { LOGGER.info("Get Entity's list, size: " + it.size) }
    }

    override fun findById(entityId: Long): DepartmentResponse {
        return entityId.let { it ->
            isExists(it)
            departmentRepository.findById(entityId).get()
                .let { mupStructMapper.toDto(it) }
        }.also { LOGGER.info("Get Entity with id $entityId") }
    }

    override fun delete(entityId: Long) {
        entityId
            .let {
                isExists(entityId)
                departmentRepository.deleteById(entityId)
            }
            .also {
                publisher.publishEvent(createEvent("Delete Some information with ID", entityId))
            }.also { LOGGER.info("Delete Entity with id $entityId") }
    }

    override fun isExists(existsById: Long) {
        require(!departmentRepository.existsById(existsById)) { "Department Id must be existing !" }
    }

    private fun toPage(departments: List<Department>): Page<DepartmentResponse> {
        val result = mutableListOf<DepartmentResponse>()
        departments.map { result.add(mupStructMapper.toDto((it))) }
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