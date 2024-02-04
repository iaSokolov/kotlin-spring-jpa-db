package ru.iasokolov.demo.model


interface TimeDependencyRecord<T> : Record<T> {
    fun period(): HrPeriod
}