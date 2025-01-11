/*
Copyright 2023 Halsyon.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.project.customer.api.dto.employee

import com.project.customer.api.dto.department.DepartmentResponse
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import java.sql.Timestamp
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Tag(name = "EmployeeResponse", description = "The Employee Response object")
data class EmployeeResponse(

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
    var department: DepartmentResponse? = null,

    @Schema(description = "Employee address", example = "John Doe")
    @field:NotBlank(message = "Address is mandatory")
    var address: String? = null,

    @Schema(description = "Employee phone", example = "(555) 555-5555")
    @field:NotBlank(message = "Phone is mandatory")
    var phone: String? = null,

    @Schema(description = "Employee email", example = "John Doe")
    @field:NotBlank(message = "Email is mandatory")
    var email: String? = null,

    @Schema(description = "Employee salary", example = "1000")
    @field:NotNull(message = "salary is mandatory")
    var salary: Double? = null,

    @Schema(description = "Employee created At", example = "2023-01-01::00:00:00")
    var createdAt: Timestamp? = null,

    @Schema(description = "Employee updatedAt", example = "2023-01-01::00:00:00")
    var updatedAt: Timestamp? = null,

    @Schema(description = "Employee periodAt", example = "2023-01-01::00:00:00")
    var periodAt: String? = null
) {
}