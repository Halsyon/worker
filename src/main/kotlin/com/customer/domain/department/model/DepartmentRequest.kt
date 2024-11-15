package com.customer.domain.department.model

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

class DepartmentRequest(
    var id: Long? = null,
    /**
     * Наименовение организации
     * Ключ "shortName" - обязательный
     * Длина - от 5 до 25 символов
     * Может содержать только символы латинского алфавита
     */
    @NotNull(message = "Key 'name' is mandatory")
    @Length(min = 5, max = 25, message = "Name length must be from 5 to 25")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only letters a-z and A-Z")
    var shortName: String? = null,
    var ceo: String? = null,
    var address: String? = null
) {
}