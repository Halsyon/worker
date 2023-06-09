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

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * @description - main model
 * @author
 */
@Entity(name = "worker")
@Table(indexes = [
    Index(name = "idx_worker_name", columnList = "name")
])
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
    var age: Long? = null,

    @field:NotBlank(message = "Department is mandatory")
    @Column(name = "department", nullable = false)
    var department: String? = null
)
