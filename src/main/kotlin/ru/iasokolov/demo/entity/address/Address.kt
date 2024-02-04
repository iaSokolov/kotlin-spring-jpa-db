package ru.iasokolov.demo.entity.address

import ru.iasokolov.demo.model.HrPeriod
import ru.iasokolov.demo.model.TimeDependencyRecord
import java.time.LocalDate
import java.util.*

class Address(
    val id: UUID,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val dataType: Int,
    val data: AddressData
) : TimeDependencyRecord<AddressData> {
    override fun period() = HrPeriod(startDate, endDate)
    override fun data() = data
    override fun dataType() = dataType
}