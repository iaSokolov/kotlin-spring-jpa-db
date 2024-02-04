package ru.iasokolov.demo.model

import java.util.UUID

interface Record<T> {
    fun id(): UUID
    fun operationIn(): Operation
    fun operationOut(): Operation?
    fun deleted(): Boolean
    fun dataType(): Int
    fun data(): T
}