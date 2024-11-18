/*
Copyright 2023 Halsyon.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.customer.domain.worker.model.view

import io.swagger.v3.oas.annotations.tags.Tag
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * @description - dto model worker
 */
@Tag(name = "WorkerResponse")
class WorkerResponse(

    var id: Long? = null,
    @field:NotNull(message = "name is mandatory")
    var name: String? = null,
    @field:NotNull(message = "age is mandatory")
    var age: Int? = null,
    @field:NotBlank(message = "Department is mandatory")
    var department: String,
    @field:NotNull(message = "salary is mandatory")
    var salary: Double? = null,
    @field:NotBlank(message = "Address is mandatory")
    var address: String? = null
) {
    fun copy(id: Int): WorkerResponse? {
        TODO("Not yet implemented")
    }
}