package com.project.customer.service

import org.springframework.data.domain.Page
import org.springframework.transaction.annotation.Transactional

@Transactional
interface BaseService<T, U> {

    fun findAll(page: Int, size: Int, sortField: String): Page<U>

    fun save(entityCreate: T): U

    fun findById(entityId: Long): U

    fun update(entityId: Long, entityUpdated: T): U

    fun delete(entityId: Long)

    fun isExists(existsById: Long)

}