package ru.iasokolov.demo.entity.persdata

import ru.iasokolov.demo.model.HrPeriod
import ru.iasokolov.demo.model.TimeDependencyRecord
import java.time.LocalDate
import java.util.*

class Persdata(
    val id: UUID,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val dataType: Int,
    val data: PersdataData
) : TimeDependencyRecord<PersdataData> {
    override fun period() = HrPeriod(startDate, endDate)
    override fun data() = data
    override fun dataType() = dataType
}