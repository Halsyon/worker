/*
Copyright [2023] [Halsyon]

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

import com.customer.domain.worker.model.Worker
import com.customer.domain.worker.repository.WorkerRepository
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional
class WorkerService(
    val workerRepository: WorkerRepository
) {
    private val logger = LoggerFactory.getLogger(WorkerService::class.java)

    fun findAll(page: Pageable): Page<Worker> {
        return workerRepository.findAll(page)
    }

    fun save(
        worker: Worker
    ): Worker = worker.let {
        //checkAccess()
        workerRepository.save(worker)
    }

    fun findById(workerId: Long): Worker =
        workerId
            .let {
                //checkAccess()
                workerRepository.findById(it)
                    .orElseThrow { RuntimeException("Worker with given id not found !") }
            }


    fun update(worker: Worker): Worker =
        worker
            .let {
                //checkAccess()
                workerRepository.save(worker)
            }.also { logger.info("Update Entity with id ${worker.name}") }


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
        //todo
        return result;
    }
}