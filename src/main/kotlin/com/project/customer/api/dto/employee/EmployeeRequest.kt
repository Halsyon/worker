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
    var id: Long? = null,

    @Schema(description = "Employee name", example = "John Doe")
    @field:NotNull(message = "name is mandatory")
    var name: String? = null,

    @Schema(description = "Employee age", example = "30")
    @field:NotNull(message = "age is mandatory")
    var age: Int? = null,

    @Schema(description = "Employee DEPARTMENT ID", example = "John Doe")
    @field:NotBlank(message = "Department is mandatory")
    var department: DepartmentRequest? = null,

    @Schema(description = "Employee salary", example = "1000")
    @field:NotNull(message = "salary is mandatory")
    var salary: Double? = null,

    @Schema(description = "Employee address", example = "John Doe")
    @field:NotBlank(message = "Address is mandatory")
    var address: String? = null,

    @Schema(description = "Employee phone", example = "(555) 555-5555")
    @field:NotBlank(message = "Phone is mandatory")
    var phone: String? = null,

    @Schema(description = "Employee email", example = "John Doe")
    @field:NotBlank(message = "Email is mandatory")
    var email: String? = null,

    @Schema(description = "Employee created At", example = "2023-01-01::00:00:00")
    var createdAt: Timestamp? = null,

    @Schema(description = "Employee updatedAt", example = "2023-01-01::00:00:00")
    var updatedAt: Timestamp? = null,

    @Schema(description = "Employee periodAt", example = "2023-01-01::00:00:00")
    var periodAt: String? = null
) {

}
