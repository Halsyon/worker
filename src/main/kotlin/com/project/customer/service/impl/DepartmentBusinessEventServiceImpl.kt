package com.project.customer.service.impl

import com.project.customer.api.dto.departmentbusinessevent.DBERequest
import com.project.customer.api.dto.departmentbusinessevent.DBEResponse
import com.project.customer.repository.DepartmentBusinessEventEntityRepository
import com.project.customer.service.BaseService
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class DepartmentBusinessEventServiceImpl(
    private val departmentBusinessEventRepository: DepartmentBusinessEventEntityRepository
) : BaseService<DBERequest, DBEResponse> {
    val logger = org.slf4j.LoggerFactory.getLogger(DepartmentBusinessEventServiceImpl::class.java)

    override fun findAll(page: Int, size: Int, sortField: String): Page<DBEResponse> {
        TODO("Not yet implemented")
    }

    override fun findById(entityId: Long): DBEResponse {
        TODO("Not yet implemented")
    }

    override fun delete(entityId: Long) {
        TODO("Not yet implemented")
    }

    override fun isExists(existsById: Long) {
        TODO("Not yet implemented")
    }

    override fun update(entityId: Long, entityUpdated: DBERequest): DBEResponse {
        TODO("Not yet implemented")
    }

    override fun save(entityCreate: DBERequest): DBEResponse {
        TODO("Not yet implemented")
    }

}