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
package com.project.customer.service.impl

import com.project.customer.domain.employee.Employee
import com.project.customer.api.dto.employee.EmployeeRequest
import com.project.customer.api.dto.employee.EmployeeResponse
import com.project.customer.repository.WorkerRepository
import com.project.customer.service.WorkerService
import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class WorkerServiceImpl(
    val workerRepository: WorkerRepository,
    val modelMapper: ModelMapper
) : WorkerService {
    private val logger = LoggerFactory.getLogger(WorkerServiceImpl::class.java)

    override fun findAll(page: Int, size: Int, name: String): Page<EmployeeResponse> {
        //checkAccess()
        val pageable: Pageable = PageRequest.of(page, size, Sort.Direction.ASC, name)

        val result: Page<Employee> = workerRepository.findAll(pageable)

        val mappedResult: Page<EmployeeResponse> = result.map { modelMapper.map(it, EmployeeResponse::class.java) }
        logger.info("Find all entities size: " + mappedResult.size)

        return mappedResult
    }

    override fun save(workerCreate: EmployeeRequest): EmployeeResponse =
        workerCreate
            .let {
                //checkAccess()
                val result = workerRepository.save(modelMapper.map(workerCreate, Employee::class.java))
                modelMapper.map(result, EmployeeResponse::class.java)
            }

    override fun findById(workerId: Long): EmployeeResponse =
        workerId
            .let {
                logger.info("Get Entity with id $workerId")
                //checkAccess()
                val result = workerRepository.findById(it)
                    .orElseThrow { RuntimeException("Worker with given id not found !") }
                modelMapper.map(result, EmployeeResponse::class.java)
            }

    override fun update(
        workerId: Long,
        workerUpdated: EmployeeRequest
    ): EmployeeResponse =
        workerUpdated
            .let {
                //checkAccess()
                val worker = workerRepository.findById(workerId)
                    .orElseThrow { RuntimeException("Worker with given id not found !") }
                worker.name = workerUpdated.name
                worker.age = workerUpdated.age
                //worker.department = Department( workerUpdated.department
                modelMapper.map(workerRepository.save(worker), EmployeeResponse::class.java)
            }.also { logger.info("Update Entity with id ${workerUpdated.name}") }


    override fun delete(employeeId: Long) {
        employeeId
            .let {
                //checkAccess()
                if (!workerRepository.existsById(it)) {
                    throw RuntimeException("Worker Id must be existing !")
                }
                workerRepository.deleteById(it)
            }
    }

    private fun checkAccess(token: String): Boolean {
        var result: Boolean = false;
        //some code
        return result;
    }
}