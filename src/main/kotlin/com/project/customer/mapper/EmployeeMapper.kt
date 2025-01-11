package com.project.customer.mapper

import com.project.customer.api.dto.employee.EmployeeRequest
import com.project.customer.api.dto.employee.EmployeeResponse
import com.project.customer.domain.employee.Employee
import org.mapstruct.Mapper

@Mapper(componentModel = "spring", uses = [DepartmentMapper::class])
interface EmployeeMapper {

    fun mapToEntity(employeeRequest: EmployeeRequest): Employee

    fun mapToDto(employee: Employee): EmployeeResponse
}