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
import com.project.customer.mapper.EmployeeMapper
import com.project.customer.repository.EmployeeRepository
import com.project.customer.service.BaseService
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class EmployeeServiceImpl(
    private val fallbackService: EmployeeFallbackServiceImpl,
    private val employeeRepository: EmployeeRepository,
    private val employeeMapper: EmployeeMapper
) : BaseService<EmployeeRequest, EmployeeResponse> {

    private val logger = LoggerFactory.getLogger(EmployeeServiceImpl::class.java)

    override fun findAll(page: Int, size: Int, sortField: String): Page<EmployeeResponse> {
        //checkAccess()
        val pageable: Pageable = PageRequest.of(page, size, Sort.Direction.ASC, sortField)

        return employeeRepository.findAll(pageable)
            .map { employeeMapper.mapToDto(it) }
            .also { logger.info("Find all entities size: " + it.size) }
    }

    override fun save(entityCreate: EmployeeRequest): EmployeeResponse =
        //checkAccess()
        entityCreate
            .let {
                employeeMapper.mapToDto(
                    employeeRepository.save(employeeMapper.mapToEntity(entityCreate))
                )
            }.also { logger.info("Save Entity with id ${entityCreate.name}") }

    override fun findById(entityId: Long): EmployeeResponse {
        //checkAccess()
        return runCatching {
            // Основная логика получения данных
            employeeRepository.findById(entityId)
                .orElseThrow { EntityNotFoundException("Entity with ID $entityId not found") }
                .let { employeeMapper.mapToDto(it) }
        }.getOrElse { exception ->
            // Fallback в случае ошибки
            logger.warn("Failed to fetch entity with id $entityId, falling back", exception)
            fallbackService.ifFallback(entityId)
        }.also { _ ->
            logger.info("Fetched or fallback entity with id: $entityId")
        }
    }

    override fun update(entityId: Long, entityUpdated: EmployeeRequest): EmployeeResponse {
        runCatching {
            isExists(entityId)
            // Убедимся, что переданное значение entityUpdated синхронизировано с entityId
            val updatedEntity = entityUpdated.apply { id = entityId }
            val savedEntity = employeeRepository.save(employeeMapper.mapToEntity(updatedEntity))

            employeeMapper.mapToDto(savedEntity)
        }.onSuccess { updatedEntity ->
            logger.info("Successfully updated entity with id: $entityId")
            return updatedEntity
        }.onFailure { exception ->
            logger.error("Failed to update entity with id: $entityId", exception)
            throw exception
        }
        throw IllegalStateException("Unreachable code reached in update method.")
    }

    override fun delete(entityId: Long) {
        runCatching {
            isExists(entityId)
            employeeRepository.deleteById(entityId)
        }.onSuccess {
            logger.info("Successfully deleted entity with id $entityId")
        }.onFailure { exception ->
            logger.error("Failed to delete entity with id $entityId", exception)
            throw exception
        }
    }

    fun findByFullName(firstName: String?, lastName: String?, middleName: String?): List<EmployeeResponse> {
        runCatching {
            employeeRepository.findByFullName(firstName, lastName, middleName)
                .toList().map { employeeMapper.mapToDto(it) }
        }
            .onSuccess { logger.info("Successfully found entity with first name $lastName") }
            .onFailure { logger.info("Failed to find entity with last name $lastName") }
        throw IllegalStateException("Unreachable code reached in update method.")
    }


    override fun isExists(existsById: Long) {
        require(employeeRepository.existsById(existsById)) { "Employee Id must be existing !, Wanted id: $existsById" }
    }

    private fun checkAccess(token: String): Boolean {
        val result: Boolean = false;
        //some code
        return result;
    }
}