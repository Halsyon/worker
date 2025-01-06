package com.project.customer.domain.departmentbusinessevent

enum class EventStatus(private val nameStatus: String, private val description: String) {
    CREATED("Created", "When the event is created"),
    ACCEPTED("Accepted", "When the event is accepted"),
    REJECTED("Rejected", "When the event is rejected");

    val status: String
        get() = this.nameStatus

    companion object {
        fun from(nameStatus: String, description: String): EventStatus? {
            return values().find { it.nameStatus == nameStatus && it.description == description }
        }
    }
}