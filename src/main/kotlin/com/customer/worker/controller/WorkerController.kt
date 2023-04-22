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
package com.customer.worker.controller

import com.customer.worker.model.Worker
import com.customer.worker.repository.WorkerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

/**
 * @description
 * @author
 */
@RestController
@RequestMapping("/worker")
class WorkerController(
    val workerRepository: WorkerRepository
) {

    @GetMapping("/{id}")
    fun getEmployee(@PathVariable id: Long): Optional<Worker> {
        return workerRepository.findById(id)
    }

    @GetMapping
    fun getAllEmployees(): Iterable<Worker> {
        return workerRepository.findAll()
    }

    @PostMapping
    fun saveEmployee(@RequestBody worker: Worker): Worker {
        return workerRepository.save(worker)
    }

    @PutMapping
    fun updateEmployee(@RequestBody worker: Worker) {
        workerRepository.save(worker)
    }

    @DeleteMapping("/{id}")
    fun deleteEmployee(@PathVariable id: Long) {
        workerRepository.deleteById(id)
    }
}