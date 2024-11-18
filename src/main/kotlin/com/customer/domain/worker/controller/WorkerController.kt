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

import com.customer.domain.worker.model.view.WorkerRequest
import com.customer.domain.worker.model.view.WorkerResponse
import com.customer.domain.worker.service.impl.WorkerServiceImpl
import org.springframework.data.domain.Page
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @description
 * @author Halsyon
 */
@Validated
@RestController
@RequestMapping("api/v1/worker")
class WorkerController(private val workerServiceImpl: WorkerServiceImpl) : WorkerApi {

    override fun getWorkersPage(page: Int, size: Int, sortField: String): Page<WorkerResponse> {
        return workerServiceImpl.findAll(page, size, sortField)
    }

    override fun getWorkerById(workerId: Long): WorkerResponse =
        workerServiceImpl.findById(workerId)

    override fun creteWorker(worker: WorkerRequest): WorkerResponse =
        workerServiceImpl.save(worker)

    override fun updateWorker(workerId: Long, worker: WorkerRequest): WorkerResponse? {
        return workerServiceImpl.update(workerId, worker)
    }

    override fun deleteWorker(workerId: Long) {
        workerServiceImpl.delete(workerId)
    }
}