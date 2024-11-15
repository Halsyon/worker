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
package com.customer.domain.worker.controller

import com.customer.domain.worker.model.view.WorkerResponse
import com.customer.domain.worker.service.WorkerService
import org.springframework.data.domain.Page
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @description
 * @author
 */
@Validated
@RestController
@RequestMapping("api/v1/worker")
class WorkerController(private val workerService: WorkerService) : WorkerApi {

    override fun getWorkerById(workerId: Long): WorkerResponse =
        workerService.findById(workerId)

    override fun creteWorker(worker: WorkerResponse): WorkerResponse =
        workerService.save(worker)

    override fun updateWorker(var1: String?, var2: WorkerResponse?): WorkerResponse? {
        TODO("Not yet implemented")
    }

    fun listApplications(var1: WorkerResponse?): Page<WorkerResponse> {
        TODO("Not yet implemented")
    }

//
//    @GetMapping
//    fun getAll(pageable: Pageable): Page<Worker> =
//        workerService.findAll(pageable)
//
//    @GetMapping("/{workerId}")
//    fun get(@PathVariable @Min(1) workerId: Long): WorkerResponse =
//        workerService.findById(workerId)
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    fun save(@Valid @RequestBody worker: WorkerResponse): WorkerResponse =
//        workerService.save(worker)
//
//    @PutMapping("{workerId}")
//    fun update(
//        @PathVariable workerId: Long,
//        @Valid @RequestBody worker: WorkerResponse
//    ): WorkerResponse = workerService.update(workerId, worker)
//
//    @DeleteMapping("/{workerId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    fun delete(@PathVariable @Min(1) workerId: Long) =
//        workerService.delete(workerId)

}