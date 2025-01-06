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
import com.project.customer.service.impl.WorkerServiceImpl
import org.springframework.data.domain.Page
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RestController

/**
 * @description
 * @author Halsyon
 */
@Validated
@RestController
class EmployeeController(private val workerServiceImpl: WorkerServiceImpl) : EmployeeApi {

    override fun getWorkersPage(page: Int, size: Int, sortField: String): Page<EmployeeResponse> {
        return workerServiceImpl.findAll(page, size, sortField)
    }

    override fun getWorkerById(employeeId: Long): EmployeeResponse =
        workerServiceImpl.findById(employeeId)

    override fun creteWorker(worker: EmployeeRequest): EmployeeResponse =
        workerServiceImpl.save(worker)

    override fun updateWorker(workerId: Long, worker: EmployeeRequest): EmployeeResponse? {
        return workerServiceImpl.update(workerId, worker)
    }

    override fun deleteWorker(workerId: Long) {
        workerServiceImpl.delete(workerId)
    }
}