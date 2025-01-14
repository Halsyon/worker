/*
Copyright 2023 Halsyon.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.project.customer.api.controller

import com.project.customer.api.contract.EmployeeApi
import com.project.customer.api.dto.employee.EmployeeRequest
import com.project.customer.api.dto.employee.EmployeeResponse
import com.project.customer.service.impl.EmployeeServiceImpl
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.RestController

@RestController
class EmployeeController(private val employeeServiceImpl: EmployeeServiceImpl) : EmployeeApi {

    override fun getEmployeesPage(page: Int, size: Int, sortField: String): Page<EmployeeResponse> {
        return employeeServiceImpl.findAll(page, size, sortField)
    }

    override fun getEmployeeById(employeeId: Long): EmployeeResponse {
        return employeeServiceImpl.findById(employeeId)
    }

    override fun findEmployee(firstName: String?, lastName: String?, middleName: String?): List<EmployeeResponse> {
        return employeeServiceImpl.findByFullName(firstName, lastName, middleName)
    }

    override fun createEmployee(employeeRequest: EmployeeRequest): EmployeeResponse =
        employeeServiceImpl.save(employeeRequest)

    override fun updateEmployee(workerId: Long, employeeRequest: EmployeeRequest): EmployeeResponse? {
        return employeeServiceImpl.update(workerId, employeeRequest)
    }

    override fun deleteEmployee(employeeId: Long) {
        employeeServiceImpl.delete(employeeId)
    }
}