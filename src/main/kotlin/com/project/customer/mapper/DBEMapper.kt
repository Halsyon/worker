package com.project.customer.mapper

import com.project.customer.api.dto.departmentbusinessevent.DBERequest
import com.project.customer.api.dto.departmentbusinessevent.DBEResponse
import com.project.customer.domain.departmentbusinessevent.DepartmentBusinessEventEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface DBEMapper {
    fun toDto(dbeEntity: DepartmentBusinessEventEntity): DBEResponse

    fun toEntity(dbeRequest: DBERequest): DepartmentBusinessEventEntity
}