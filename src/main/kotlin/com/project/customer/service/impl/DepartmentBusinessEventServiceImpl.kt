package com.project.customer.service.impl

import com.project.customer.api.dto.departmentbusinessevent.DBERequest
import com.project.customer.api.dto.departmentbusinessevent.DBEResponse
import com.project.customer.domain.departmentbusinessevent.EventStatus
import com.project.customer.mapper.DBEMapper
import com.project.customer.repository.DepartmentBusinessEventEntityRepository
import com.project.customer.service.BaseService
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service

@Service
class DepartmentBusinessEventServiceImpl(
    private val eventRepository: DepartmentBusinessEventEntityRepository,
    private val mupStructMapper: DBEMapper
) : BaseService<DBERequest, DBEResponse> {
    val logger = org.slf4j.LoggerFactory.getLogger(DepartmentBusinessEventServiceImpl::class.java)

    override fun findAll(page: Int, size: Int, sortField: String): Page<DBEResponse> {
        TODO("Not yet implemented")
    }

    override fun findById(entityId: Long): DBEResponse {
        return entityId
            .let {
                isExists(it)
                eventRepository.findById(it).get()
            }
            .let { mupStructMapper.toDto(it) }
            .also { logger.info("Get Entity with id $entityId") }
    }

    override fun delete(entityId: Long) {
        entityId
            .let {
                isExists(it)
                eventRepository.deleteById(it)
            }.also { logger.info("Delete Entity with id $entityId") }
    }

    override fun update(entityId: Long, entityUpdated: DBERequest): DBEResponse {
        return entityId
            .let {
                isExists(it)
                entityUpdated.eventStatus = EventStatus.UPDATED
                entityUpdated.id = it
                eventRepository.save(mupStructMapper.toEntity(entityUpdated))
            }
            .let { mupStructMapper.toDto(it) }
            .also { logger.info("Update Entity with id $entityId") }
    }

    override fun save(entityCreate: DBERequest): DBEResponse {
        //TODO ("Not yet implemented")
        return DBEResponse()
    }

    override fun isExists(existsById: Long) {
        require(eventRepository.existsById(existsById)) { "Department Business Event Id must be existing !" }
    }
}