package com.customer.domain.worker.service

import com.customer.domain.worker.model.view.WorkerRequest
import com.customer.domain.worker.model.view.WorkerResponse
import org.springframework.data.domain.Page

interface WorkerService {
    fun findAll(page: Int, size: Int, name: String): Page<WorkerResponse>
    fun save(workerCreate: WorkerRequest): WorkerResponse
    fun findById(workerId: Long): WorkerResponse
    fun update(workerId: Long, workerUpdated: WorkerRequest): WorkerResponse
    fun delete(workerId: Long)
}