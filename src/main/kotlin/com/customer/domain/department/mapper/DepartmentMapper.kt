package com.customer.domain.department.mapper

import com.customer.domain.department.model.*;
import com.customer.domain.department.model.Department
import com.customer.domain.department.model.DepartmentRequest
import com.customer.domain.department.model.DepartmentResponse
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface DepartmentMapper {
    fun toDto(department: Department): DepartmentResponse
    fun toBean(department: DepartmentRequest): Department
}