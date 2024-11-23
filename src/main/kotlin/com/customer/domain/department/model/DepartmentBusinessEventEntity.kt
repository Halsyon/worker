package com.customer.domain.department.model

import javax.persistence.*

@Entity
@Table(name = "department_event", schema = "worker")
data class DepartmentBusinessEventEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @Column(name = "name", nullable = false)
    var name: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    var department: Department? = null,

    @Column(name = "event_type", nullable = false)
    var eventType: String? = null,

    @Column(name = "event_status", nullable = false)
    var eventStatus: EventStatus? = null,

    @Column(name = "event_data", nullable = false)
    var eventData: String? = null
) {

}