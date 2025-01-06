package com.project.customer.service

import com.project.customer.api.dto.department.DepartmentRequest
import com.project.customer.api.dto.department.DepartmentResponse
import org.springframework.data.domain.Page
import org.springframework.transaction.annotation.Transactional

interface DepartmentService {

    @Transactional
    fun save(departmentRequest: DepartmentRequest): DepartmentResponse

    @Transactional
    fun getAll(page: Int, size: Int, sortField: String): Page<DepartmentResponse>

    @Transactional
    fun getById(id: Long): DepartmentResponse

    @Transactional
    fun update(id: Long, departmentRequest: DepartmentRequest): DepartmentResponse

    @Transactional
    fun delete(id: Long)
}