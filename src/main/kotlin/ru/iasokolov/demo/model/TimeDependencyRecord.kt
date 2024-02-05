package ru.iasokolov.demo.model


interface TimeDependencyRecord<T> : Record<T> {
    val period: HrPeriod
}