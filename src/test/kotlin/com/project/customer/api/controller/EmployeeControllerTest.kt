package com.project.customer.api.controller

import com.project.customer.api.dto.department.DepartmentRequest
import com.project.customer.api.dto.department.DepartmentResponse
import com.project.customer.api.dto.employee.EmployeeRequest
import com.project.customer.api.dto.employee.EmployeeResponse
import com.project.customer.service.impl.WorkerServiceImpl
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.*
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

@SpringBootTest
class EmployeeControllerTest {

    private val workerServiceImpl: WorkerServiceImpl = mock(WorkerServiceImpl::class.java)
    private val workerController = EmployeeController(workerServiceImpl)


    @Test
    fun `getAll should return all workers`() {
      //  val pageable: Pageable = mock(Pageable::class.java)
        val page = 1
        val size = 10
        val sort = "name"
        val pageable: Pageable = PageRequest.of(page, size, Sort.Direction.fromString(sort))
        val workers = listOf(WorkerView(id = 1, name = "John Doe"))
        val workers1 = listOf(
            EmployeeResponse(
                id = 1,
                name = "John Doe",
                age = 27,
                department = DepartmentResponse(
                    id = 1,
                    shortName = "Umbrella Corp",
                    ceo = "Alice Marcus",
                    address = "Raccoon city, USA"
                ),
                address = "Raccoon city, USA",
                salary = 1000.0
            )
        )
        val pageRslt: Page<EmployeeResponse> = PageImpl(workers1)

        `when`(workerServiceImpl.findAll(page, size, sort)).thenReturn(pageRslt)

        val result = workerController.getWorkersPage(page, size, sort)

        assertEquals(result.content.size, 1)
        assertEquals(result.content[0].name, "John Doe")
    }

    private fun WorkerView(id: Long?, name: String): EmployeeResponse {
        return WorkerView(id = id, name = name)
    }

    private fun getWorkerRequest(): EmployeeRequest {
       val worker =
            EmployeeRequest(
                id = 1,
                name = "John Doe",
                age = 27,
                department = DepartmentRequest(
                    id = 1,
                    shortName = "Umbrella Corp",
                    ceo = "Alice Marcus",
                    address = "Raccoon city, USA"
                ),
                address = "Raccoon city, USA",
                salary = 1000.0
            )
        return worker
    }

    private fun getWorkerResponse(): EmployeeResponse {
        val worker =
            EmployeeResponse(
                id = 1,
                name = "John Doe",
                age = 27,
                department = DepartmentResponse(
                    id = 1,
                    shortName = "Umbrella Corp",
                    ceo = "Alice Marcus",
                    address = "Raccoon city, USA"
                ),
                address = "Raccoon city, USA",
                salary = 1000.0
            )
        return worker
    }

    @Test
    fun `getAll should return empty page when no workers found`() {
        val pageable: Pageable = mock(Pageable::class.java)
        val page: Page<EmployeeResponse> = PageImpl(emptyList())

        `when`(workerServiceImpl.findAll(1, 10, "name")).thenReturn(page)

        val result = workerController.getWorkersPage(1, 10, "name")

        assertTrue(result.content.isEmpty())
    }

    @Test
    fun `get should return worker when valid ID is provided`() {
        val workerId = 1L
        val worker = WorkerView(id = workerId, name = "John Doe")

        `when`(workerServiceImpl.findById(workerId)).thenReturn(worker)

        val result = workerController.getWorkerById(workerId)

        assertEquals(result.name, "John Doe")
    }

    @Test
    fun `get should throw exception when worker not found`() {
        val workerId = 999L

        `when`(workerServiceImpl.findById(workerId)).thenThrow(ResponseStatusException(HttpStatus.NOT_FOUND))

        assertThrows<ResponseStatusException> { workerController.getWorkerById(workerId) }
    }

    @Test
    fun `save should create a new worker`() {
        val worker =
            EmployeeRequest(
                id = 1,
                name = "John Doe",
                age = 27,
                department = DepartmentRequest(
                    id = 1,
                    shortName = "Umbrella Corp",
                    ceo = "Alice Marcus",
                    address = "Raccoon city, USA"
                ),
                address = "Raccoon city, USA",
                salary = 1000.0
            )
        val employeeResponse = EmployeeResponse(
            id = 1,
            name = "John Doe",
            age = 27,
            department = DepartmentResponse(
                id = 1,
                shortName = "Umbrella Corp",
                ceo = "Alice Marcus",
                address = "Raccoon city, USA"
            ),
            address = "Raccoon city, USA",
            salary = 1000.0
        )
        `when`(workerServiceImpl.save(worker)).thenReturn(employeeResponse)

        val result = workerController.creteWorker(worker)

        assertNotNull(result.id)
        assertEquals(result.name, "John Doe")
    }

    @Test
    fun `update should modify an existing worker`() {
        val workerId = 1L
        val worker = getWorkerRequest()
        val workerResponse = getWorkerResponse()

        `when`(workerServiceImpl.update(workerId, worker)).thenReturn(workerResponse)

        val result = workerController.updateWorker(workerId, worker)

        assertEquals(result?.name, "John Doe")
    }

    @Test
    fun `update should throw exception when worker not found`() {
        val workerId = 999L
        val worker = getWorkerRequest()

        `when`(workerServiceImpl.update(workerId, worker)).thenThrow(ResponseStatusException(HttpStatus.NOT_FOUND))

        assertThrows<ResponseStatusException> { workerController.updateWorker(workerId, worker) }
    }

    @Test
    fun `delete should remove a worker when valid ID is provided`() {
        val workerId = 1L

        doNothing().`when`(workerServiceImpl).delete(workerId)

        assertDoesNotThrow { workerController.deleteWorker(workerId) }
        verify(workerServiceImpl, times(1)).delete(workerId)
    }

    @Test
    fun `delete should throw exception when worker not found`() {
        val workerId = 999L

        doThrow(ResponseStatusException(HttpStatus.NOT_FOUND)).`when`(workerServiceImpl).delete(workerId)

        assertThrows<ResponseStatusException> { workerController.deleteWorker(workerId) }
    }
}