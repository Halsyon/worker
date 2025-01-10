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

import com.project.customer.api.dto.employee.EmployeeRequest
import com.project.customer.api.dto.employee.EmployeeResponse
import com.project.customer.domain.employee.Employee
import com.project.customer.repository.WorkerRepository
import com.project.customer.service.BaseService
import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.transaction.annotation.Transactional

@Transactional
class EmployeeServiceImpl(
    val workerRepository: WorkerRepository,
    val modelMapper: ModelMapper
) : BaseService<EmployeeRequest, EmployeeResponse> {

    private val logger = LoggerFactory.getLogger(EmployeeServiceImpl::class.java)

    override fun findAll(page: Int, size: Int, sortField: String): Page<EmployeeResponse> {
        //checkAccess()
        val pageable: Pageable = PageRequest.of(page, size, Sort.Direction.ASC, sortField)

        return workerRepository.findAll(pageable)
            .map { modelMapper.map(it, EmployeeResponse::class.java) }
            .also { logger.info("Find all entities size: " + it.size) }
    }

    override fun save(entityCreate: EmployeeRequest): EmployeeResponse =
        //checkAccess()
        entityCreate
            .let {
                val result = workerRepository.save(modelMapper.map(entityCreate, Employee::class.java))
                modelMapper.map(result, EmployeeResponse::class.java)
            }.also { logger.info("Save Entity with id ${entityCreate.name}") }

    override fun findById(entityId: Long): EmployeeResponse =
        //checkAccess()
        entityId
            .let {
                val result = workerRepository.findById(entityId)
                    .orElseThrow { RuntimeException("Worker with given id not found !") }
                modelMapper.map(result, EmployeeResponse::class.java)
            }.also { logger.info("Get Entity with id $entityId") }


    override fun update(
        entityId: Long,
        entityUpdated: EmployeeRequest
    ): EmployeeResponse {
        //checkAccess()
        return entityId
            .let {
                isExists(it)

                entityUpdated.id = it
                modelMapper.map(
                    workerRepository.save(modelMapper.map(entityUpdated, Employee::class.java)),
                    EmployeeResponse::class.java
                )
            }.also { logger.info("Update Entity with id: ${entityUpdated.id} !") }
    }

    override fun delete(entityId: Long) {
        //checkAccess()
        isExists(entityId)
            .let { workerRepository.deleteById(entityId) }
            .also { logger.info("Delete Entity with id $entityId !") }
    }

    override fun isExists(existsById: Long) {
        return require(!workerRepository.existsById(existsById)) { "Employee Id must be existing !, Wanted id: $existsById" }

    }

    private fun checkAccess(token: String): Boolean {
        val result: Boolean = false;
        //some code
        return result;
    }
}