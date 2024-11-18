package com.customer.domain.department.model

class DepartmentResponse(
    private var id: Long? = null,
    private var shortName: String? = null,
    private var ceo: String? = null,
    private var address: String? = null
) {
    override fun toString(): String {
        return "DepartmentResponse(id=$id, shortName=$shortName, ceo=$ceo, address=$address)"
    }
}