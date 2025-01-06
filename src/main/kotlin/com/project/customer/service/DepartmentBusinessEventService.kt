package com.project.customer.service

import com.project.customer.domain.departmentbusinessevent.DepartmentBusinessEventEntity

interface DepartmentBusinessEventService {
    fun save(departmentBusinessEvent: DepartmentBusinessEventEntity)
    fun getAll(page: Int, size: Int, sortField: String): List<DepartmentBusinessEventEntity>
    fun delete(departmentBusinessEvent: DepartmentBusinessEventEntity)
    fun getById(id: Long): DepartmentBusinessEventEntity
}