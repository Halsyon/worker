package com.project.customer.repository;

import com.project.customer.domain.departmentbusinessevent.DepartmentBusinessEventEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DepartmentBusinessEventEntityRepository : JpaRepository<DepartmentBusinessEventEntity, Long> {
}