package ru.iasokolov.demo.model

interface Record<T> {
    fun dataType(): Int
    fun data(): T
}