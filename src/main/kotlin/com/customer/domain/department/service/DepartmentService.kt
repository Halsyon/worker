package com.customer.domain.department.service

import com.customer.domain.department.model.DepartmentRequest
import com.customer.domain.department.model.DepartmentResponse
import org.springframework.data.domain.Page

interface DepartmentService {

    fun save(departmentRequest: DepartmentRequest): DepartmentResponse
    fun getAll(): Page<DepartmentResponse>
    fun getById(id: Long): DepartmentResponse
    fun update(id: Long, departmentRequest: DepartmentRequest): DepartmentResponse
    fun delete(id: Long)
}