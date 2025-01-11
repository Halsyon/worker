package com.project.customer.service.impl

import com.project.customer.api.dto.department.DepartmentResponse
import com.project.customer.api.dto.employee.EmployeeResponse
import com.project.customer.service.FallbackService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service
class EmployeeFallbackServiceImpl : FallbackService<Long, EmployeeResponse> {

    private val logger = LoggerFactory.getLogger(EmployeeFallbackServiceImpl::class.java)
    override fun ifFallback(entityId: Long): EmployeeResponse {
        logger.warn("Using fallback for entity with id: $entityId")
        // Пример возврата дефолтного объекта
        return EmployeeResponse(
            id = entityId,
            name = "Fallback Employee",
            department = DepartmentResponse(
                shortName = "Unknown Corporation",
                ceo = "Unknown CEO",
                address = "Unknown Address",
                id = null
            ),
            salary = 0.0,
            createdAt = Timestamp.valueOf(java.time.LocalDateTime.now())
        )
    }
}