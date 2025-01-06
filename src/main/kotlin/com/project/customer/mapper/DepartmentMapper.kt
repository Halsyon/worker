package com.project.customer.mapper

import com.project.customer.domain.department.Department
import com.project.customer.api.dto.department.DepartmentRequest
import com.project.customer.api.dto.department.DepartmentResponse
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface DepartmentMapper {

    fun toDto(department: Department): DepartmentResponse

    fun toBean(department: DepartmentRequest): Department
}