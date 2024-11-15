package com.customer.domain.department.controller

import com.customer.domain.department.model.DepartmentRequest
import com.customer.domain.department.model.DepartmentResponse
import com.customer.domain.error.ServiceErrorMessage
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.Size

/**
 * @description: DepartmentApi interface
 */
@Validated
@Tag(name = "DepartmentApi", description = "the Department API")
interface DepartmentApi {

    @Operation(
        operationId = "getAllDepartment",
        summary = "get all data for card department",
        tags = ["department-controller"],
        responses = [ApiResponse(
            responseCode = "200",
            description = "return all objects",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = DepartmentResponse::class)
            )]
        ), ApiResponse(
            responseCode = "403",
            description = "FORBIDDEN",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = ServiceErrorMessage::class)
            )]
        ), ApiResponse(responseCode = "404", description = "NOT_FOUND"), ApiResponse(
            responseCode = "422",
            description = "UNPROCESSABLE_ENTITY",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = ServiceErrorMessage::class)
            )]
        ), ApiResponse(
            responseCode = "500",
            description = "INTERNAL_SERVER_ERROR",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = ServiceErrorMessage::class)
            )]
        )],
        security = [SecurityRequirement(name = "EpaAuth")]
    )
    @RequestMapping(
        method = [RequestMethod.GET],
        value = [],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    @Parameter(
        name = "workerId", //fixme add default value
        description = "ID of the Department",
        required = true
    )
    fun getAllDepartment(): Page<DepartmentResponse> //fixme add default value


    @Operation(
        operationId = "getDepartment",
        summary = "get all data for card department by ID",
        tags = ["department-controller"],
        responses = [ApiResponse(
            responseCode = "200",
            description = "return id or request object after update",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = DepartmentResponse::class)
            )]
        ), ApiResponse(
            responseCode = "403",
            description = "FORBIDDEN",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = ServiceErrorMessage::class)
            )]
        ), ApiResponse(responseCode = "404", description = "NOT_FOUND"), ApiResponse(
            responseCode = "422",
            description = "UNPROCESSABLE_ENTITY",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = ServiceErrorMessage::class)
            )]
        ), ApiResponse(
            responseCode = "500",
            description = "INTERNAL_SERVER_ERROR",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = ServiceErrorMessage::class)
            )]
        )],
        security = [SecurityRequirement(name = "EpaAuth")]
    )
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/{departmentId}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    @Parameter(
        name = "workerId",
        description = "ID of the Department",
        required = true
    )
    fun getDepartmentById(@Valid @PathVariable("departmentId") @Min(1) departmentId: Long):DepartmentResponse

    @Operation(
        operationId = "createDepartment",
        summary = "create new data for card department from view",
        tags = ["department-controller"],
        responses = [ApiResponse(
            responseCode = "200",
            description = "return id or request object after update",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = DepartmentResponse::class)
            )]
        )]
    )
    @RequestMapping(
        method = [RequestMethod.POST],
        value = [],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun creteDepartment(
        @Parameter(
            name = "WorkerView",
            description = "View Worker model"
        ) @RequestBody(required = true) @Valid department: DepartmentRequest
    ): DepartmentResponse

    @Operation(
        operationId = "updateDepartment",
        summary = "update worker information",
        tags = ["department-controller"],
        responses = [ApiResponse(
            responseCode = "200",
            description = "OK",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = DepartmentResponse::class)
            )]
        ), ApiResponse(
            responseCode = "403",
            description = "FORBIDDEN",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = ServiceErrorMessage::class)
            )]
        ), ApiResponse(responseCode = "404", description = "NOT_FOUND"), ApiResponse(
            responseCode = "422",
            description = "UNPROCESSABLE_ENTITY",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = ServiceErrorMessage::class)
            )]
        ), ApiResponse(
            responseCode = "500",
            description = "INTERNAL_SERVER_ERROR",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = ServiceErrorMessage::class)
            )]
        )],
        security = [SecurityRequirement(name = "EpaAuth")]
    )
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/{departmentId}"],
        produces = ["application/json"]
    )
    fun updateDepartment(
        @Parameter(
            name = "departmentId",
            description = "ID of the Department",
            required = true
        ) @PathVariable("departmentId") @Min(1) @Size(min = 1, max = 1000000) departmentId: Long,
        @Parameter(
            name = "DepartmentResponse",
            description = "Optional worker response model for updates",
            required = false
        ) @RequestBody(required = false) @Valid department: DepartmentRequest
    ): DepartmentResponse
}