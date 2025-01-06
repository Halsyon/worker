package com.project.customer.event

import com.project.customer.api.dto.departmentbusinessevent.DepartmentBusinessEvent
import com.project.customer.domain.departmentbusinessevent.EventStatus
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class DepartmentEventPublisher(
    val publisher: ApplicationEventPublisher,
) {

    companion object {
      private  val LOGGER = org.slf4j.LoggerFactory.getLogger(DepartmentEventPublisher::class.java)
    }

    fun publishEvent(event: DepartmentBusinessEvent) {
        LOGGER.info("Publishing event: $event")
        publisher.publishEvent(event)
    }
}

fun main() {
    val status = EventStatus.from(nameStatus = "Created", description = "When the event is created")
    println(status) // EventStatus.CREATED
 }