/*
Copyright 2023 Halsyon

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
package com.customer.domain.worker.service

import com.customer.domain.worker.model.entity.Worker
import com.customer.domain.worker.model.view.WorkerView
import com.customer.domain.worker.repository.WorkerRepository
import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class WorkerService(
    val workerRepository: WorkerRepository,
    val modelMapper: ModelMapper
) {
    private val logger = LoggerFactory.getLogger(WorkerService::class.java)

    fun findAll(page: Pageable): Page<Worker> {
        //checkAccess()
        return workerRepository.findAll(page)
    }

    fun save(workerCreate: WorkerView): WorkerView =
        workerCreate
            .let {
                //checkAccess()
                val result = workerRepository.save(modelMapper.map(workerCreate, Worker::class.java))
                modelMapper.map(result, WorkerView::class.java)
            }

    fun findById(workerId: Long): WorkerView =
        workerId
            .let {
                //checkAccess()
                val result = workerRepository.findById(it)
                    .orElseThrow { RuntimeException("Worker with given id not found !") }
                modelMapper.map(result, WorkerView::class.java)
            }


    fun update(
        workerId: Long,
        workerUpdated: WorkerView
    ): WorkerView =
        workerUpdated
            .let {
                //checkAccess()
                val worker = workerRepository.findById(workerId)
                    .orElseThrow { RuntimeException("Worker with given id not found !") }
                worker.name = workerUpdated.name
                worker.age = workerUpdated.age
                worker.department = workerUpdated.department
                modelMapper.map(workerRepository.save(worker), WorkerView::class.java)
            }.also { logger.info("Update Entity with id ${workerUpdated.name}") }


    fun delete(employeeId: Long) {
        employeeId
            .let {
                //checkAccess()
                if (!workerRepository.existsById(it)) {
                    throw RuntimeException("Worker Id must be existing !")
                }
                workerRepository.deleteById(it)
            }
    }

    private fun checkAccess(token: String): Boolean {
        var result: Boolean = false;
        //some code
        return result;
    }
}