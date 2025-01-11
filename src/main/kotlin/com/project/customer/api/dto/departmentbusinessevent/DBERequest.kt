package com.project.customer.api.dto.departmentbusinessevent

import com.project.customer.domain.department.Department
import com.project.customer.domain.departmentbusinessevent.EventStatus
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "DBERequest", description = "The DBE Request model")
data class DBERequest(

    @Schema(description = "DBE id", example = "1")
    var id: Long? = null,

    @Schema(description = "DBE name", example = "Send DBE on email")
    var name: String? = null,

    @Schema(description = "DBE department", example = "Umbrella Corp")
    var department: Department? = null,

    @Schema(description = "DBE eventType", example = "Created DBE")
    var eventType: String? = null,

    @Schema(description = "DBE eventStatus", example = "Accepted")
    var eventStatus: EventStatus? = null,

    @Schema(description = "DBE eventData", example = "2023-01-01::00:00:00")
    var eventData: String? = null
)
