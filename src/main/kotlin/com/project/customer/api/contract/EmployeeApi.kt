package com.project.customer.api.contract

import com.project.customer.handler.BaseServiceErrorMessage
import com.project.customer.api.dto.employee.EmployeeRequest
import com.project.customer.api.dto.employee.EmployeeResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Min

@Validated
@Tag(name = "Employee-Api", description = "The Employee API")
@RequestMapping("api/v1/employee")
interface EmployeeApi {

    @Operation(
        operationId = "listWorkers",
        summary = "Get all data for card worker by page",
        tags = ["Employee-Api"],
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Returns paginated list of workers",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = EmployeeResponse::class)
                    )
                ]
            )
        ]
    )
    @RequestMapping(
        method = [RequestMethod.GET],
        value = [],
        produces = ["application/json"]
    )
    fun getEmployeesPage(
        @Parameter(
            name = "page",
            description = "Page number (0-based)",
            example = "0"
        ) @RequestParam(required = false, defaultValue = "0") page: Int,

        @Parameter(
            name = "size",
            description = "Number of items per page",
            example = "10"
        ) @RequestParam(required = false, defaultValue = "10") size: Int,

        @Parameter(
            name = "sort",
            description = "Sorting criteria in the format: property(,asc|desc). Default is ascending.",
            example = "lastName,asc"
        ) @RequestParam(required = false, defaultValue = "id,asc") sortField: String
    ): Page<EmployeeResponse>

    @Operation(
        operationId = "getWorker",
        summary = "Get worker data by ID",
        tags = ["Employee-Api"],
        responses = [ApiResponse(
            responseCode = "200",
            description = "Return worker object",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = EmployeeResponse::class)
            )]
        )]
    )
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/{employeeId}"],
        produces = ["application/json"]
    )
    fun getEmployeeById(@PathVariable("employeeId") @Min(1) employeeId: Long): EmployeeResponse


    @Operation(
        operationId = "createWorker",
        summary = "create new data for card worker from view",
        tags = ["Employee-Api"],
        responses = [ApiResponse(
            responseCode = "200",
            description = "return id or request object after update",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = EmployeeResponse::class)
            )]
        )]
    )
    @RequestMapping(
        method = [RequestMethod.POST],
        value = [],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createEmployee(
        @Parameter(
            name = "WorkerView",
            description = "View Worker model"
        ) @RequestBody(required = true) @Valid worker: EmployeeRequest
    ): EmployeeResponse

    @Operation(
        operationId = "updateWorker",
        summary = "update worker information",
        tags = ["Employee-Api"],
        responses = [ApiResponse(
            responseCode = "200",
            description = "OK",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = EmployeeResponse::class)
            )]
        ), ApiResponse(
            responseCode = "403",
            description = "FORBIDDEN",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = BaseServiceErrorMessage::class)
            )]
        ), ApiResponse(responseCode = "404", description = "NOT_FOUND"), ApiResponse(
            responseCode = "422",
            description = "UNPROCESSABLE_ENTITY",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = BaseServiceErrorMessage::class)
            )]
        ), ApiResponse(
            responseCode = "500",
            description = "INTERNAL_SERVER_ERROR",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = BaseServiceErrorMessage::class)
            )]
        )],
        security = [SecurityRequirement(name = "EpaAuth")]
    )
    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/{workerId}"],
        produces = ["application/json"]
    )
    fun updateEmployee(
        @Parameter(
            name = "workerId",
            description = "ID of the worker",
            required = true
        ) @PathVariable("workerId") workerId: Long,
        @Parameter(
            name = "WorkerResponse",
            description = "Optional worker response model for updates",
            required = false
        ) @RequestBody(required = true) @Valid  worker: EmployeeRequest
    ): EmployeeResponse?

    @Operation(
        operationId = "deleteWorker",
        summary = "delete worker information",
        tags = ["Employee-Api"],
        responses = [ApiResponse(
            responseCode = "200",
            description = "OK",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = EmployeeResponse::class)
            )]
        ), ApiResponse(
            responseCode = "403",
            description = "FORBIDDEN",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = BaseServiceErrorMessage::class)
            )]
        ), ApiResponse(responseCode = "404", description = "NOT_FOUND"), ApiResponse(
            responseCode = "422",
            description = "UNPROCESSABLE_ENTITY",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = BaseServiceErrorMessage::class)
            )]
        ), ApiResponse(
            responseCode = "500",
            description = "INTERNAL_SERVER_ERROR",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = BaseServiceErrorMessage::class)
            )]
        )],
        security = [SecurityRequirement(name = "EpaAuth")]
    )
    @RequestMapping(
        consumes = ["application/json"],
        method = [RequestMethod.DELETE],
        value = ["/{workerId}"]
    )
    fun deleteEmployee(@PathVariable("workerId") @Min(1) workerId: Long)
}