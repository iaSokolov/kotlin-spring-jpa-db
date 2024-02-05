package ru.iasokolov.demo.entity.address

import ru.iasokolov.demo.model.HrPeriod
import ru.iasokolov.demo.model.Operation
import ru.iasokolov.demo.model.TimeDependencyRecord
import java.time.LocalDate
import java.util.*

class Address(
    val id: UUID,
    val startDate: LocalDate,
    val endDate: LocalDate,
    var deleted: Boolean = false,
    val opIdIn: UUID,
    var opIdOut: UUID? = null,
    val dataType: Int,
    val data: AddressData
) : TimeDependencyRecord<AddressData> {

    override val recordDataType: Int
        get() = this.dataType

    override val recordOperationIn: Operation
        get() = Operation(this.opIdIn)

    override var recordOperationOut: Operation?
        get() = this.opIdOut?.let { Operation(it) }
        set(value) {
            this.opIdOut = value?.id
        }

    override var recordDeleted: Boolean
        get() = this.deleted
        set(value) {
            this.deleted = value
        }

    override val recordData: AddressData
        get() = this.data

    override val period: HrPeriod
        get() = HrPeriod(
            startDate = this.startDate,
            endDate = this.endDate
        )
}