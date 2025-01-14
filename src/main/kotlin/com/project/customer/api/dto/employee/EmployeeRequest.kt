package com.project.customer.api.dto.employee

import com.project.customer.api.dto.department.DepartmentRequest
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import java.sql.Timestamp
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Tag(name = "EmployeeRequest", description = "The Employee Request model")
data class EmployeeRequest(

    @Schema(description = "Employee id", example = "1")
    var id: Long?,

    @Schema(description = "Employee name", example = "John")
    @field:NotNull(message = "firstName is mandatory")
    var firstName: String?,

    @Schema(description = "Employee name", example = "Doe")
    @field:NotNull(message = "middleName is mandatory")
    var middleName: String?,

    @Schema(description = "Employee name", example = "Smith")
    @field:NotNull(message = "lastName is mandatory")
    var lastName: String?,

    @Schema(description = "Employee age", example = "30")
    @field:NotNull(message = "age is mandatory")
    var age: Int?,

    @Schema(description = "Employee DEPARTMENT ID", example = "John Doe")
    @field:NotBlank(message = "Department is mandatory")
    var department: DepartmentRequest?,

    @Schema(description = "Employee address", example = "John Doe")
    @field:NotBlank(message = "Address is mandatory")
    var address: String?,

    @Schema(description = "Employee phone", example = "(555) 555-5555")
    @field:NotBlank(message = "Phone is mandatory")
    var phone: String?,

    @Schema(description = "Employee email", example = "John Doe")
    @field:NotBlank(message = "Email is mandatory")
    var email: String?,

    @Schema(description = "Employee salary", example = "1000")
    @field:NotNull(message = "salary is mandatory")
    var salary: Double?,

    @Schema(description = "Employee created At", example = "2023-01-01::00:00:00")
    @field:NotBlank(message = "createdAt is mandatory")
    var createdAt: Timestamp?,

    @Schema(description = "Employee updatedAt", example = "2023-01-01::00:00:00")
    @field:NotBlank(message = "updatedAt is mandatory")
    var updatedAt: Timestamp?,

    @Schema(description = "Дата окончания договора", example = "2023-01-01::00:00:00")
    @field:NotBlank(message = "periodAt is mandatory")
    var periodAt: String?
) {

}
