package ru.iasokolov.demo.model

import java.util.*

data class TimeDependencySlice<T>(
    val opIdIn: UUID,
    val opIdOut: UUID? = null,
    val delete: Boolean = false,
    val dataType: Int,
    val period: HrPeriod,
    val data: T
)