/*
Copyright 2023 Halsyon

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
package com.customer.domain.worker.model.entity

import com.customer.domain.department.model.Department
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * main model
 * @author
 */
@Entity
@Table(
    indexes = [
        Index(name = "idx_worker_name", columnList = "name")
    ], name = "worker", schema = "worker"
)
data class Worker(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "worker_id", nullable = false)
    var id: Long? = null,

    @field:NotBlank(message = "Department is mandatory")
    @Column(name = "name", nullable = false)
    var name: String? = null,

    @field:NotNull(message = "age is mandatory")
    @Column(name = "age", nullable = false)
    var age: Int? = null,

    @ManyToOne()
    @JoinColumn(name = "department_id")
    var department: Department,

    @Column(name = "address", nullable = false)
    var address: String? = null,

    @Column(name = "salary", nullable = false)
    var salary: Double? = null
) {

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(  id = $id   ,   name = $name   ,   age = $age   ,   department = $department   ,   address = $address   ,   salary = $salary )"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Worker

        if (id != other.id) return false
        if (name != other.name) return false
        if (age != other.age) return false
        if (department != other.department) return false
        if (address != other.address) return false
        if (salary != other.salary) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (age ?: 0)
        result = 31 * result + department.hashCode()
        result = 31 * result + (address?.hashCode() ?: 0)
        result = 31 * result + (salary?.hashCode() ?: 0)
        return result
    }
}
