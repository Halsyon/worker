package com.customer.domain.department.repository;

import com.customer.domain.department.model.DepartmentBusinessEventEntity
import org.springframework.data.jpa.repository.JpaRepository

interface DepartmentBusinessEventEntityRepository : JpaRepository<DepartmentBusinessEventEntity, Long> {
}