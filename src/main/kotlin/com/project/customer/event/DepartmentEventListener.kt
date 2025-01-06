package com.project.customer.event

import com.project.customer.api.dto.departmentbusinessevent.DepartmentBusinessEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class DepartmentEventListener() {

    companion object {
        private val LOGGER = org.slf4j.LoggerFactory.getLogger(DepartmentEventListener::class.java)
    }

    @EventListener(condition = "#event.success")
    fun onApplicationBusinessEvent(event: DepartmentBusinessEvent) {
        LOGGER.info("Listening event: $event")
        var department: Any = event.sourceObject
        var payload: String = event.payload

        TODO("Not yet implemented")
    }
}