package com.project.customer.api.dto.department

import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

@Tag(name = "DepartmentRequest", description = "The Department Request model")
data class DepartmentRequest(

    @Schema(description = "Department id", example = "1")
    var id: Long? = null,

    /**
     * Наименование организации
     * Ключ "shortName" - обязательный
     * Длина - от 5 до 25 символов
     * Может содержать только символы латинского алфавита
     */
    @NotNull(message = "Key 'name' is mandatory")
    @Length(min = 5, max = 25, message = "Name length must be from 5 to 25")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only letters a-z and A-Z")
    @Schema(description = "Organization name", example = "Umbrella Corp")
    var shortName: String? = null,

    @Schema(description = "CEO name", example = "Given Nealson")
    var ceo: String? = null,

    @Schema(description = "Department address", example = "Sequeira de Abril, 123")
    var address: String? = null
) {
}