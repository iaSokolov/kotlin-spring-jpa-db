package ru.iasokolov.demo.model

data class TimeDependencySlice<T>(
    val dataType: Int,
    val period: HrPeriod,
    val data: T
)