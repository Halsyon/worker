package com.project.customer.api.controller

import com.project.customer.api.contract.DepartmentApi
import com.project.customer.api.dto.department.DepartmentRequest
import com.project.customer.api.dto.department.DepartmentResponse
import com.project.customer.service.impl.DepartmentServiceImpl
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.RestController

@RestController
class DepartmentController(
    private val departmentServiceImpl: DepartmentServiceImpl
) : DepartmentApi {


    override fun getAllDepartments(page: Int, size: Int, sortField: String): Page<DepartmentResponse> {
        return departmentServiceImpl.getAll(page, size, sortField)
    }

    override fun getDepartmentById(departmentId: Long): DepartmentResponse {
        return departmentServiceImpl.getById(departmentId)
    }

    override fun creteDepartment(department: DepartmentRequest): DepartmentResponse {
        return departmentServiceImpl.save(department)
    }

    override fun updateDepartment(departmentId: Long, department: DepartmentRequest): DepartmentResponse {
        return departmentServiceImpl.update(departmentId, department)
    }
}