package com.customer.domain.worker.controller

import com.customer.domain.error.ServiceErrorMessage
import com.customer.domain.worker.model.view.WorkerRequest
import com.customer.domain.worker.model.view.WorkerResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.Size

/**
 * @description: WorkerApi interface
 */
@Validated
@Tag(name = "WorkerApi", description = "the Worker API")
interface WorkerApi {

    @Operation(
        operationId = "listWorkers",
        summary = "Get all data for card worker by page",
        tags = ["worker-controller"],
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Returns paginated list of workers",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = WorkerResponse::class)
                    )
                ]
            )
        ]
    )
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/api/v1/workers"],
        produces = ["application/json"]
    )
    fun getWorkersPage(
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
    ): Page<WorkerResponse>

    @Operation(
        operationId = "getWorker",
        summary = "get all data for card worker by ID",
        tags = ["worker-controller"],
        responses = [ApiResponse(
            responseCode = "200",
            description = "return id or request object after update",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = WorkerResponse::class)
            )]
        )]
    )
    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/{workerId}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    @Parameter(
        name = "workerId",
        description = "ID of the worker",
        required = true
    )
    fun getWorkerById(@PathVariable("workerId") @Min(1) workerId: Long): WorkerResponse

    @Operation(
        operationId = "createWorker",
        summary = "create new data for card worker from view",
        tags = ["worker-controller"],
        responses = [ApiResponse(
            responseCode = "200",
            description = "return id or request object after update",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = WorkerResponse::class)
            )]
        )]
    )
    @RequestMapping(
        method = [RequestMethod.POST],
        value = [],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun creteWorker(
        @Parameter(
            name = "WorkerView",
            description = "View Worker model"
        ) @RequestBody(required = true) @Valid worker: WorkerRequest
    ): WorkerResponse

    @Operation(
        operationId = "updateWorker",
        summary = "update worker information",
        tags = ["worker-controller"],
        responses = [ApiResponse(
            responseCode = "200",
            description = "OK",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = WorkerResponse::class)
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
        value = ["/{workerId}"],
        produces = ["application/json"]
    )
    fun updateWorker(
        @Parameter(
            name = "workerId",
            description = "ID of the worker",
            required = true
        ) @PathVariable("workerId") workerId: Long,
        @Parameter(
            name = "WorkerResponse",
            description = "Optional worker response model for updates",
            required = false
        ) @RequestBody(required = true) @Valid  worker: WorkerRequest
    ): WorkerResponse?

    @Operation(
        operationId = "deleteWorker",
        summary = "delete worker information",
        tags = ["worker-controller"],
        responses = [ApiResponse(
            responseCode = "200",
            description = "OK",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = WorkerResponse::class)
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
        consumes = ["application/json"],
        method = [RequestMethod.DELETE],
        value = ["/{workerId}"]
    )
    fun deleteWorker(@PathVariable("workerId") @Min(1) workerId: Long)
}