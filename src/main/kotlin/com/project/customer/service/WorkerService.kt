package com.project.customer.service

import com.project.customer.api.dto.employee.EmployeeRequest
import com.project.customer.api.dto.employee.EmployeeResponse
import org.springframework.data.domain.Page

interface WorkerService {
    fun findAll(page: Int, size: Int, name: String): Page<EmployeeResponse>
    fun save(workerCreate: EmployeeRequest): EmployeeResponse
    fun findById(workerId: Long): EmployeeResponse
    fun update(workerId: Long, workerUpdated: EmployeeRequest): EmployeeResponse
    fun delete(workerId: Long)
}