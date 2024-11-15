package com.customer.domain.worker.controller

import com.customer.domain.department.model.Department
import com.customer.domain.worker.model.entity.Worker
import com.customer.domain.worker.model.view.WorkerResponse
import com.customer.domain.worker.service.WorkerService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

@SpringBootTest
//internal class WorkerControllerTest {
class WorkerControllerTest {

    private val workerService: WorkerService = mock(WorkerService::class.java)
    private val workerController = WorkerController(workerService)


    @Test
    fun `getAll should return all workers`() {
        val pageable: Pageable = mock(Pageable::class.java)
        val workers = listOf(WorkerView(id = 1, name = "John Doe"))
        val workers1 = listOf(
            Worker(
                id = 1,
                name = "John Doe",
                age = 27,
                department = Department(
                    id = 1,
                    shortName = "Umbrella Corp",
                    ceo = "Alice Marcus",
                    address = "Raccoon city, USA"
                )
            )
        )
        val page: Page<Worker> = PageImpl(workers1)

        `when`(workerService.findAll(pageable)).thenReturn(page)

        val result = workerController.getAll(pageable)

        assertEquals(result.content.size, 1)
        assertEquals(result.content[0].name, "John Doe")
    }

    private fun WorkerView(id: Long?, name: String): WorkerResponse {
        return WorkerView(id = id, name = name)
    }

    @Test
    fun `getAll should return empty page when no workers found`() {
        val pageable: Pageable = mock(Pageable::class.java)
        val page: Page<Worker> = PageImpl(emptyList())

        `when`(workerService.findAll(pageable)).thenReturn(page)

        val result = workerController.getAll(pageable)

        assertTrue(result.content.isEmpty())
    }

    @Test
    fun `get should return worker when valid ID is provided`() {
        val workerId = 1L
        val worker = WorkerView(id = workerId, name = "John Doe")

        `when`(workerService.findById(workerId)).thenReturn(worker)

        val result = workerController.get(workerId)

        assertEquals(result.name, "John Doe")
    }

    @Test
    fun `get should throw exception when worker not found`() {
        val workerId = 999L

        `when`(workerService.findById(workerId)).thenThrow(ResponseStatusException(HttpStatus.NOT_FOUND))

        assertThrows<ResponseStatusException> { workerController.get(workerId) }
    }

    @Test
    fun `save should create a new worker`() {
        val worker = WorkerView(id = null, name = "John Doe")

        `when`(workerService.save(worker)).thenReturn(worker.copy(id = 1))

        val result = workerController.save(worker)

        assertNotNull(result.id)
        assertEquals(result.name, "John Doe")
    }

    @Test
    fun `update should modify an existing worker`() {
        val workerId = 1L
        val worker = WorkerView(id = workerId, name = "Jane Doe")

        `when`(workerService.update(workerId, worker)).thenReturn(worker)

        val result = workerController.update(workerId, worker)

        assertEquals(result.name, "Jane Doe")
    }

    @Test
    fun `update should throw exception when worker not found`() {
        val workerId = 999L
        val worker = WorkerView(id = workerId, name = "Jane Doe")

        `when`(workerService.update(workerId, worker)).thenThrow(ResponseStatusException(HttpStatus.NOT_FOUND))

        assertThrows<ResponseStatusException> { workerController.update(workerId, worker) }
    }

    @Test
    fun `delete should remove a worker when valid ID is provided`() {
        val workerId = 1L

        doNothing().`when`(workerService).delete(workerId)

        assertDoesNotThrow { workerController.delete(workerId) }
        verify(workerService, times(1)).delete(workerId)
    }

    @Test
    fun `delete should throw exception when worker not found`() {
        val workerId = 999L

        doThrow(ResponseStatusException(HttpStatus.NOT_FOUND)).`when`(workerService).delete(workerId)

        assertThrows<ResponseStatusException> { workerController.delete(workerId) }
    }
}