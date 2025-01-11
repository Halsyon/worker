package com.project.customer.service


interface FallbackService<T, U> {
    fun ifFallback(t: T): U
}