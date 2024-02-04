package ru.iasokolov.demo.model

data class TimeDependencyRecordSlice<T>(
    val dataType: Int,
    val period: HrPeriod,
    val data: T
) : TimeDependencyRecord<T> {
    override fun period() = period
    override fun data() = data
    override fun dataType() = dataType
}