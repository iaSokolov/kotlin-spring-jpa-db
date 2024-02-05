package ru.iasokolov.demo.entity.persdata

import ru.iasokolov.demo.service.TimeDependencyRecordFactory
import ru.iasokolov.demo.model.TimeDependencySlice
import java.util.*

class PersdataFactory : TimeDependencyRecordFactory<PersdataData, Persdata> {
    override fun record(slice: TimeDependencySlice<PersdataData>): Persdata {
        return Persdata(
            id = UUID.randomUUID(),
            startDate = slice.period.startDate,
            endDate = slice.period.endDate,
            opIdIn = slice.opIdIn,
            dataType = slice.dataType,
            data = slice.data
        )
    }
}