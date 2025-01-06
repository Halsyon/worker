package com.project.customer.service.impl

import com.project.customer.domain.departmentbusinessevent.DepartmentBusinessEventEntity
import com.project.customer.repository.DepartmentBusinessEventEntityRepository
import com.project.customer.service.DepartmentBusinessEventService
import org.springframework.stereotype.Service

@Service
class DepartmentBusinessEventServiceImpl(
    private val departmentBusinessEventRepository: DepartmentBusinessEventEntityRepository
): DepartmentBusinessEventService {
    override fun save(departmentBusinessEvent: DepartmentBusinessEventEntity) {
        TODO("Not yet implemented")
    }

    override fun getAll(page: Int, size: Int, sortField: String): List<DepartmentBusinessEventEntity> {
        TODO("Not yet implemented")
    }

    override fun delete(departmentBusinessEvent: DepartmentBusinessEventEntity) {
        TODO("Not yet implemented")
    }

    override fun getById(id: Long): DepartmentBusinessEventEntity {
        TODO("Not yet implemented")
    }
}