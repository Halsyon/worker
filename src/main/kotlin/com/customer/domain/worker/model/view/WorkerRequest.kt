package com.customer.domain.worker.model.view

import com.customer.domain.department.model.DepartmentRequest
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "WorkerRequest", description = "the Worker API")
data class WorkerRequest(
    val id: Int,
    val name: String,
    val age: Int,
    val department: DepartmentRequest,
    val address: String,
    val salary: Double
) {

}
