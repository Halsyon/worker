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

import com.customer.domain.worker.model.Worker
import com.customer.domain.worker.service.WorkerService
import org.springframework.data.domain.Pageable
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
    fun getAll(pageable: Pageable): Iterable<Worker> {
        return workerService.findAll(pageable)
    }

    @GetMapping("/{id}")
    fun get(@PathVariable @Min(1) id: Long): Worker {
        return workerService.findById(id)
    }

    @PostMapping
    fun save(@Valid @RequestBody worker: Worker): Worker = workerService.save(worker)

    @PutMapping
    fun update(@Valid @RequestBody worker: Worker): Worker = workerService.update(worker)

    @DeleteMapping("/{id}")
    fun deleteEmployee(@PathVariable @Min(1) id: Long) {
        workerService.delete(id)
    }
}