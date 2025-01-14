package com.project.customer.api.contract

import com.project.customer.api.dto.department.DepartmentRequest
import com.project.customer.api.dto.department.DepartmentResponse
import com.project.customer.handler.BaseServiceErrorMessage
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
import javax.validation.constraints.Size

@RequestMapping("api/v1/department", produces = ["application/json"])
@Validated
@Tag(name = "Department-Api", description = "the Department API")
interface DepartmentApi {

    @Operation(
        operationId = "getAllDepartment",
        summary = "get all data for card department",
        tags = ["Department-Api"],
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
    @GetMapping(value = [], produces = ["application/json"])
    fun getAllDepartments(
        @Parameter(
            name = "page", description = "Page number (0-based)", example = "0"
        ) @RequestParam(required = false, defaultValue = "0") page: Int,
        @Parameter(
            name = "size", description = "Number of items per page", example = "10"
        ) @RequestParam(required = false, defaultValue = "10") size: Int,
        @Parameter(
            name = "sort",
            description = "Sorting criteria in the format: property(,asc|desc). Default is ascending.",
            example = "lastName,asc"
        ) @RequestParam(required = false, defaultValue = "id,asc") sortField: String
    ): Page<DepartmentResponse>


    @Operation(
        operationId = "getDepartment",
        summary = "Get data for department card by ID",
        tags = ["Department-Api"],
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Returns department data by ID",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = DepartmentResponse::class)
                    )
                ]
            ),
            ApiResponse(
                responseCode = "403",
                description = "Forbidden",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = BaseServiceErrorMessage::class)
                    )
                ]
            ),
            ApiResponse(responseCode = "404", description = "Department not found"),
            ApiResponse(
                responseCode = "422",
                description = "Unprocessable Entity",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = BaseServiceErrorMessage::class)
                    )
                ]
            ),
            ApiResponse(
                responseCode = "500",
                description = "Internal Server Error",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(implementation = BaseServiceErrorMessage::class)
                    )
                ]
            )
        ],
        security = [SecurityRequirement(name = "EpaAuth")]
    )
    @GetMapping("/{departmentId}", produces = ["application/json"])
    @Parameter(
        name = "departmentId",
        description = "ID of the department",
        required = true
    )
    fun getDepartmentById(
        @Valid @PathVariable("departmentId") @Min(1) departmentId: Long
    ): DepartmentResponse

    @Operation(
        operationId = "createDepartment",
        summary = "Create new Entity by from view",
        tags = ["Department-Api"],
        responses = [ApiResponse(
            responseCode = "201",
            description = "Return response object after create new object",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = DepartmentResponse::class)
            )]
        )]
    )
    @PostMapping(value = [], produces = ["application/json"])
    fun creteDepartment(
        @Parameter(
            name = "DepartmentView",
            description = "View Department request model"
        ) @RequestBody(required = true) @Valid department: DepartmentRequest
    ): DepartmentResponse

    @Operation(
        operationId = "updateDepartment",
        summary = "update worker information",
        tags = ["Department-Api"],
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
    @PutMapping(value = ["/{departmentId}"], produces = ["application/json"])
    fun updateDepartment(
        @Parameter(
            name = "departmentId",
            description = "ID of the Department",
            required = true
        ) @Valid @PathVariable("departmentId") @Min(1) departmentId: Long,
        @Parameter(
            name = "DepartmentResponse",
            description = "Optional Department response model for updates",
            required = false
        ) @RequestBody(required = true) @Valid department: DepartmentRequest
    ): DepartmentResponse

    @Operation(
        operationId = "deleteDepartment",
        summary = "delete worker information",
        tags = ["Department-Api"],
        responses = [ApiResponse(
            responseCode = "204",
            description = "No Content",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = Unit::class)
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
    @DeleteMapping(value = ["/{departmentId}"], produces = ["application/json"])
    fun deleteDepartment(
        @Parameter(
            name = "departmentId",
            description = "ID of the Department",
            required = true
        ) @Valid @PathVariable("departmentId") @Min(1) @Size(min = 1, max = 1000000) departmentId: Long
    )

}