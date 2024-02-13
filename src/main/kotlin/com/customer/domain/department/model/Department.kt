package com.customer.domain.department.model

import javax.persistence.*

@Entity
@Table(name = "department", schema = "worker")
data class Department(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private var id: Long? = null,

    @Column(nullable = false)
    private var shortName: String? = null,

    @Column(nullable = false)
    private var ceo: String? = null,

    @Column(nullable = false)
    private var address: String? = null
) {
}