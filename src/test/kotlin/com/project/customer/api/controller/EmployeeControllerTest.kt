package com.project.customer.api.controller

import com.project.customer.api.dto.department.DepartmentRequest
import com.project.customer.api.dto.department.DepartmentResponse
import com.project.customer.api.dto.employee.EmployeeRequest
import com.project.customer.api.dto.employee.EmployeeResponse
import com.project.customer.service.impl.EmployeeServiceImpl
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

    private val employeeServiceImpl: EmployeeServiceImpl = mock(EmployeeServiceImpl::class.java)
    private val workerController = EmployeeController(employeeServiceImpl)


    @Test
    fun `getAll should return all employees`() {
        //  val pageable: Pageable = mock(Pageable::class.java)
        val page = 1
        val size = 10
        val sort = "name"
        val pageable: Pageable = PageRequest.of(page, size, Sort.Direction.ASC, sort)
        val totalElements: Long = 100


        val responseList = listOf(employeeResponse())

        val employeeResponsePage: Page<EmployeeResponse> = PageImpl(responseList, pageable, totalElements)

        `when`(employeeServiceImpl.findAll(page, size, sort)).thenReturn(employeeResponsePage)

        val result = workerController.getEmployeesPage(page, size, sort)

        assertEquals(result.content.size, 1)
        assertEquals(result.content[0].firstName, "John")
    }


    @Test
    fun `getAll should return empty page when no employee found`() {
        val pageable: Pageable = mock(Pageable::class.java)
        val page: Page<EmployeeResponse> = PageImpl(emptyList())

        `when`(employeeServiceImpl.findAll(1, 10, "name")).thenReturn(page)

        val result = workerController.getEmployeesPage(1, 10, "name")

        assertTrue(result.content.isEmpty())
    }

    @Test
    fun `get should return employee when valid ID is provided`() {
        val entityId = 1L
        val employeeResponse = getEmployeeResponse(id = entityId, name = "John Doe Smith")

        `when`(employeeServiceImpl.findById(entityId)).thenReturn(employeeResponse)

        val result = workerController.getEmployeeById(entityId)

        assertEquals(result.middleName, "Doe")
        assertEquals(result.lastName, "Smith")
    }

    @Test
    fun `get should return Employee by fullName should return employee when valid name fields is provided`() {

        val employeeName = "John"
        val employeeMiddleName = "Doe"
        val employeeLastName = "Smith"
        val employeeResponse =
            listOf(getEmployeeResponse(id = 1L, name = "$employeeName $employeeMiddleName $employeeLastName"))

        `when`(employeeServiceImpl.findByFullName(employeeName, employeeMiddleName, employeeLastName)).thenReturn(
            employeeResponse
        )

        val result = workerController.findEmployee(employeeName, employeeMiddleName, employeeLastName)

        assertEquals(result[0].firstName, "John")
        assertEquals(result[0].middleName, "Doe")
        assertEquals(result[0].lastName, "Smith")
    }

    @Test
    fun `get should throw exception when employee not found`() {
        val entityId = 999L

        `when`(employeeServiceImpl.findById(entityId)).thenThrow(ResponseStatusException(HttpStatus.NOT_FOUND))

        assertThrows<ResponseStatusException> { workerController.getEmployeeById(entityId) }
    }

    @Test
    fun `save should create a new worker`() {
        val employeeRequest = getEmployeeRequest()

        val employeeResponse = employeeResponse()

        `when`(employeeServiceImpl.save(employeeRequest)).thenReturn(employeeResponse)

        val result = workerController.createEmployee(employeeRequest)

        assertNotNull(result.id)
        assertEquals(result.firstName, "John")
    }

    @Test
    fun `update should modify an existing worker`() {
        val workerId = 1L
        val worker = getEmployeeRequest()
        val workerResponse = employeeResponse()

        `when`(employeeServiceImpl.update(workerId, worker)).thenReturn(workerResponse)

        val result = workerController.updateEmployee(workerId, worker)

        assertEquals(result?.firstName, "John")
    }

    @Test
    fun `update should throw exception when worker not found`() {
        val workerId = 999L
        val worker = getEmployeeRequest()

        `when`(employeeServiceImpl.update(workerId, worker)).thenThrow(ResponseStatusException(HttpStatus.NOT_FOUND))

        assertThrows<ResponseStatusException> { workerController.updateEmployee(workerId, worker) }
    }

    @Test
    fun `delete should remove a worker when valid ID is provided`() {
        val workerId = 1L

        doNothing().`when`(employeeServiceImpl).delete(workerId)

        assertDoesNotThrow { workerController.deleteEmployee(workerId) }
        verify(employeeServiceImpl, times(1)).delete(workerId)
    }

    @Test
    fun `delete should throw exception when worker not found`() {
        val workerId = 999L

        doThrow(ResponseStatusException(HttpStatus.NOT_FOUND)).`when`(employeeServiceImpl).delete(workerId)

        assertThrows<ResponseStatusException> { workerController.deleteEmployee(workerId) }
    }

    private fun getEmployeeResponse(id: Long?, name: String): EmployeeResponse {
        return employeeResponse()
            .let { it ->
                it.id = id;
                it.firstName = name.split(" ")[0]; it.middleName = name.split(" ")[1]; it.lastName =
                name.split(" ")[2]; it
            }
    }

    private fun getEmployeeRequest(): EmployeeRequest {
        val worker =
            EmployeeRequest(
                id = 1,
                firstName = "John",
                middleName = "Doe",
                lastName = "Smith",
                age = 27,
                department = DepartmentRequest(
                    id = 1,
                    shortName = "Umbrella Corp",
                    ceo = "Alice Marcus",
                    address = "Raccoon city, USA"
                ),
                address = "Raccoon city, USA",
                salary = 1000.0,
                phone = null,
                email = null,
                createdAt = null,
                updatedAt = null,
                periodAt = null
            )
        return worker
    }

    private fun employeeResponse(): EmployeeResponse {
        val worker =
            EmployeeResponse(
                id = 1,
                firstName = "John",
                middleName = "Doe",
                lastName = "Smith",
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
}