package ru.iasokolov.demo.entity.address

import ru.iasokolov.demo.service.TimeDependencyRecordFactory
import ru.iasokolov.demo.model.TimeDependencyRecordSlice
import java.util.*

class AddressFactory : TimeDependencyRecordFactory<AddressData, Address> {
    override fun record(slice: TimeDependencyRecordSlice<AddressData>): Address {
        return Address(
            id = UUID.randomUUID(),
            startDate = slice.period.startDate,
            endDate = slice.period.endDate,
            dataType = slice.dataType,
            data = slice.data
        )
    }
}