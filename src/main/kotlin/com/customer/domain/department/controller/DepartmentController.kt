package com.customer.domain.department.controller

import com.customer.domain.department.model.DepartmentRequest
import com.customer.domain.department.model.DepartmentResponse
import com.customer.domain.department.service.DepartmentServiceImpl
import com.customer.domain.worker.model.view.WorkerResponse
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("api/v1/department")
@RestController
class DepartmentController(
    private val departmentServiceImpl: DepartmentServiceImpl
) : DepartmentApi {


    override fun getAllDepartment(): Page<DepartmentResponse> {
        return departmentServiceImpl.getAll()
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