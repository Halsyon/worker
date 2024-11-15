package com.customer.domain.worker.controller

import com.customer.domain.error.ServiceErrorMessage
import com.customer.domain.worker.model.view.WorkerResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.Size

/**
 * @description: WorkerApi interface
 */
@Validated
@Tag(name = "WorkerApi", description = "the Worker API")
interface WorkerApi {
//FIXME
//    @Operation(
//        operationId = "listWorkers",
//        summary = "get all data for card worker by filter",
//        tags = ["worker-controller"],
//        responses = [ApiResponse(
//            responseCode = "200",
//            description = "return id or request object after update",
//            content = [Content(
//                mediaType = "application/json",
//                schema = Schema(implementation = WorkerResponse::class)
//            )]
//        )]
//    )
//    @RequestMapping(
//        method = [RequestMethod.GET],
//        value = [],
//        produces = ["application/json"],
//        consumes = ["application/json"]
//    )
//    fun getWorkersPage(
//        @Parameter(
//            name = "pageable",
//            description = "Pageable",
//        ) @RequestBody(required = false) var1: @Valid WorkerResponse?
//    ): ResponseEntity<List<WorkerResponse?>?>?

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
    fun getWorkerById(@PathVariable("workerId") @Min(1)  workerId: Long): WorkerResponse

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
        ) @RequestBody(required = true) @Valid worker: WorkerResponse
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
        ) @PathVariable("workerId") var1: @Size(max = 100) String?,
        @Parameter(
            name = "WorkerResponse",
            description = "Optional worker response model for updates",
            required = false
        ) @RequestBody(required = false) var2: @Valid WorkerResponse?
    ): WorkerResponse?
}