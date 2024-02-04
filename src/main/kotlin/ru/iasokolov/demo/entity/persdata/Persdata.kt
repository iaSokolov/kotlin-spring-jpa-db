package ru.iasokolov.demo.entity.persdata

import ru.iasokolov.demo.model.HrPeriod
import ru.iasokolov.demo.model.Operation
import ru.iasokolov.demo.model.TimeDependencyRecord
import java.time.LocalDate
import java.util.*

class Persdata(
    val id: UUID,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val deleted: Boolean,
    val opIdIn: UUID,
    val opIdOut: UUID?,
    val dataType: Int,
    val data: PersdataData
) : TimeDependencyRecord<PersdataData> {
    override fun id() = id
    override fun dataType() = dataType
    override fun period() = HrPeriod(startDate, endDate)
    override fun deleted() = deleted
    override fun operationIn() = Operation(id = opIdIn)
    override fun operationOut() = opIdOut?.let { Operation(id = id) }
    override fun data() = data
}