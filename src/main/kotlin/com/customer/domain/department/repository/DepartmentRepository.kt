package com.customer.domain.department.repository;

import com.customer.domain.department.model.Department
import org.springframework.data.jpa.repository.JpaRepository


interface DepartmentRepository : JpaRepository<Department, Long> {
}