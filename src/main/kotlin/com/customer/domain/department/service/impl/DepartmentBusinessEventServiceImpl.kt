package com.customer.domain.department.service.impl

import com.customer.domain.department.model.DepartmentBusinessEventEntity
import com.customer.domain.department.repository.DepartmentBusinessEventEntityRepository
import com.customer.domain.department.service.DepartmentBusinessEventService
import org.springframework.stereotype.Service

@Service
class DepartmentBusinessEventServiceImpl(
    private val departmentBusinessEventRepository: DepartmentBusinessEventEntityRepository
): DepartmentBusinessEventService {
    override fun save(departmentBusinessEvent: DepartmentBusinessEventEntity) {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<DepartmentBusinessEventEntity> {
        TODO("Not yet implemented")
    }

    override fun delete(departmentBusinessEvent: DepartmentBusinessEventEntity) {
        TODO("Not yet implemented")
    }

    override fun getById(id: Long): DepartmentBusinessEventEntity {
        TODO("Not yet implemented")
    }
}