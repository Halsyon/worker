package com.project.customer.mapper

import com.project.customer.domain.department.Department
import com.project.customer.api.dto.department.DepartmentRequest
import com.project.customer.api.dto.department.DepartmentResponse
import org.mapstruct.Mapper

import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface DepartmentMapper {

//    @Mapping(source = "ceo", target = "ceo")
//    @Mapping(source = "address", target = "address")
    fun toDto(department: Department): DepartmentResponse

    fun toBean(department: DepartmentRequest): Department
}