package com.customer.domain.department.event

import com.customer.domain.department.model.DepartmentBusinessEvent
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