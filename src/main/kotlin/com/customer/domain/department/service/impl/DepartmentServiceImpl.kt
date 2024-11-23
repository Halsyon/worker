package com.customer.domain.department.service.impl

import com.customer.domain.department.event.DepartmentEventPublisher
import com.customer.domain.department.mapper.DepartmentMapper
import com.customer.domain.department.model.DepartmentBusinessEvent
import com.customer.domain.department.model.Department
import com.customer.domain.department.model.DepartmentRequest
import com.customer.domain.department.model.DepartmentResponse
import com.customer.domain.department.repository.DepartmentRepository
import com.customer.domain.department.service.DepartmentService
import org.modelmapper.ModelMapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.stereotype.Service

//@Slf4j
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

    override fun getAll(): Page<DepartmentResponse> {
        val departments: List<Department> = departmentRepository.findAll()
        LOGGER.info("Get Entity's list, size: " + departments.size)
        return toPage(departments) // departments
    }

    override fun save(departmentRequest: DepartmentRequest): DepartmentResponse {
        return modelMapper.map(
            departmentRepository.save(mupStructMapper.toBean(departmentRequest)),
            DepartmentResponse::class.java
        )
    }

    override fun getById(id: Long): DepartmentResponse {
        val department = departmentRepository.findById(id)
            .orElseThrow { RuntimeException("Department with given id not found !") }

        LOGGER.info("Get Entity with id $id")
        return modelMapper.map(department, DepartmentResponse::class.java)

    }

    override fun update(id: Long, departmentRequest: DepartmentRequest): DepartmentResponse {
        var departmentPersist = departmentRepository.findById(id)
            .orElseThrow { RuntimeException("Department with given id not found !") }
//fixme перемапить все поля из запроса в найденную сущность
        var departmentResult = mupStructMapper.toBean(departmentRequest).apply {
            this.setId(id)
        }

        departmentPersist.setId(id)
        //  logger.info("Update Entity with id $id").
        return modelMapper.map(departmentRepository.save(departmentPersist), DepartmentResponse::class.java)
    }

    override fun delete(id: Long) {
        departmentRepository.deleteById(id)
        LOGGER.info("Delete Entity with id $id")

        val event = DepartmentBusinessEvent(
            sourceObject = this,
            payload = "Delete Some information with id $id"
        )
        publisher.publishEvent(event)
    }

    private fun toPage(departments: List<Department>): Page<DepartmentResponse> {
        val result = mutableListOf<DepartmentResponse>()
        departments.map { result.add(modelMapper.map(it, DepartmentResponse::class.java)) }
        val page: PageImpl<DepartmentResponse> = PageImpl(result)
        return page
    }
}