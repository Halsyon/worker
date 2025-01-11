package com.project.customer.api.dto.department

import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "DepartmentResponse", description = "The Department Response object")
data class DepartmentResponse(

    @Schema(description = "Department id", example = "1")
    var id: Long?,

    @Schema(description = "Organization name", example = "Umbrella Corp")
    var shortName: String?,

    @Schema(description = "CEO name", example = "Given Nealson")
    var ceo: String?,

    @Schema(description = "Department address", example = "Sequeira de Abril, 123")
    var address: String?
) {
//    override fun toString(): String {
//        return "DepartmentResponse(id=$id, shortName=$shortName, ceo=$ceo, address=$address)"
//    }
}