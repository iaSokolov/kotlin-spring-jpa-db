package ru.iasokolov.demo.entity.address

import ru.iasokolov.demo.service.TimeDependencyRecordFactory
import ru.iasokolov.demo.model.TimeDependencySlice
import java.util.*

class AddressFactory : TimeDependencyRecordFactory<AddressData, Address> {
    override fun record(
        slice: TimeDependencySlice<AddressData>
    ): Address {
        return Address(
            id = UUID.randomUUID(),
            startDate = slice.period.startDate,
            opIdIn = slice.opIdIn,
            opIdOut = slice.opIdOut,
            endDate = slice.period.endDate,
            dataType = slice.dataType,
            data = slice.data
        )
    }
}