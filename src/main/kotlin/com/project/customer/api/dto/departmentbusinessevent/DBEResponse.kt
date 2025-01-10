package com.project.customer.api.dto.departmentbusinessevent

import com.project.customer.domain.department.Department
import com.project.customer.domain.departmentbusinessevent.EventStatus
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "DBEResponse", description = "The DBE Response object")
data class DBEResponse(
    val id: Long? = null,
    var name: String? = null,
    var department: Department? = null,
    var eventType: String? = null,
    var eventStatus: EventStatus? = null,
    var eventData: String? = null
)
