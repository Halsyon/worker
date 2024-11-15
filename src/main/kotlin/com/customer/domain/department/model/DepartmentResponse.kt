package com.customer.domain.department.model

class DepartmentResponse(
    var id: Long? = null,
    var shortName: String? = null,
    var ceo: String? = null,
    var address: String? = null
) {
    override fun toString(): String {
        return "DepartmentResponse(id=$id, shortName=$shortName, ceo=$ceo, address=$address)"
    }
}