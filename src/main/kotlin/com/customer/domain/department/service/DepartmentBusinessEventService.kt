package com.customer.domain.department.service

import com.customer.domain.department.model.DepartmentBusinessEventEntity

interface DepartmentBusinessEventService {
    fun save(departmentBusinessEvent: DepartmentBusinessEventEntity)
    fun getAll(): List<DepartmentBusinessEventEntity>
    fun delete(departmentBusinessEvent: DepartmentBusinessEventEntity)
    fun getById(id: Long): DepartmentBusinessEventEntity
}