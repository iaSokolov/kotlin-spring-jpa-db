package ru.iasokolov.demo.service

import org.springframework.stereotype.Service
import ru.iasokolov.demo.model.HrPeriod
import ru.iasokolov.demo.model.TimeDependencyRecord
import ru.iasokolov.demo.model.TimeDependencyRecordSlice

@Service
class TimeDependencyRecordService {
    fun <T, R> sliceRecords(
        newRecord: TimeDependencyRecord<T>,
        currentRecords: List<TimeDependencyRecord<T>>,
        func: (TimeDependencyRecordSlice<T>) -> R
    ): List<R> {
        val sliceRecords = mutableListOf<TimeDependencyRecordSlice<T>>()

        currentRecords.forEach { record ->
            if (record.period().startDate < newRecord.period().startDate) {
                val slice = TimeDependencyRecordSlice(
                    dataType = record.dataType(),
                    period = HrPeriod(
                        startDate = record.period().startDate,
                        endDate = newRecord.period().startDate.minusDays(1L)
                    ),
                    data = record.data()
                )
                sliceRecords.add(slice)
            }

            if (record.period().endDate > newRecord.period().endDate) {
                val slice = TimeDependencyRecordSlice(
                    dataType = record.dataType(),
                    period = HrPeriod(
                        startDate = newRecord.period().endDate.plusDays(1L),
                        endDate = record.period().endDate
                    ),
                    data = record.data()
                )
                sliceRecords.add(slice)
            }
        }

        return sliceRecords.map { func(it) }
    }

    fun <T, R> sliceRecords(
        newRecord: TimeDependencyRecord<T>,
        currentRecords: List<TimeDependencyRecord<T>>,
        factory: TimeDependencyRecordFactory<T, R>
    ): List<R> {
        val sliceRecords = mutableListOf<TimeDependencyRecordSlice<T>>()

        currentRecords.forEach { record ->
            if (record.period().startDate < newRecord.period().startDate) {
                val slice = TimeDependencyRecordSlice(
                    dataType = record.dataType(),
                    period = HrPeriod(
                        startDate = record.period().startDate,
                        endDate = newRecord.period().startDate.minusDays(1L)
                    ),
                    data = record.data()
                )
                sliceRecords.add(slice)
            }

            if (record.period().endDate > newRecord.period().endDate) {
                val slice = TimeDependencyRecordSlice(
                    dataType = record.dataType(),
                    period = HrPeriod(
                        startDate = newRecord.period().endDate.plusDays(1L),
                        endDate = record.period().endDate
                    ),
                    data = record.data()
                )
                sliceRecords.add(slice)
            }
        }

        return sliceRecords.map { factory.record(it) }
    }
}