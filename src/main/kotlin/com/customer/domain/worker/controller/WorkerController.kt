/*
Copyright (c) 2016-2023 VMware Inc. or its affiliates, All Rights Reserved. [Halsyon]

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
package com.customer.domain.worker.controller

import com.customer.domain.worker.model.entity.Worker
import com.customer.domain.worker.model.view.WorkerView
import com.customer.domain.worker.service.WorkerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Min

/**
 * @description
 * @author
 */
@Validated
@RestController
@RequestMapping("/v1/worker")
class WorkerController(
    val workerService: WorkerService
) {

    @GetMapping
    fun getAll(pageable: Pageable): Page<Worker> {
        return workerService.findAll(pageable)
    }

    @GetMapping("/{workerId}")
    fun get(@PathVariable @Min(1) workerId: Long): WorkerView {
        return workerService.findById(workerId)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@Valid @RequestBody worker: WorkerView): WorkerView =
        workerService.save(worker)

    @PutMapping("{workerId}")
    fun update(
        @PathVariable workerId: Long,
        @Valid @RequestBody worker: WorkerView
    ): WorkerView = workerService.update(workerId, worker)

    @DeleteMapping("/{workerId}")
    fun delete(@PathVariable @Min(1) workerId: Long) {
        workerService.delete(workerId)
    }
}