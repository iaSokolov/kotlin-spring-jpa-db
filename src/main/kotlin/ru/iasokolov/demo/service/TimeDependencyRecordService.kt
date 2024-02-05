package ru.iasokolov.demo.service

import org.springframework.stereotype.Service
import ru.iasokolov.demo.model.HrPeriod
import ru.iasokolov.demo.model.TimeDependencyRecord
import ru.iasokolov.demo.model.TimeDependencySlice
import java.util.*

@Service
class TimeDependencyRecordService {
    fun <T, R> sliceRecords(
        opId: UUID,
        newRecord: TimeDependencyRecord<T>,
        currentRecords: List<TimeDependencyRecord<T>>,
        func: (TimeDependencySlice<T>) -> R
    ): List<R> {
        val oldRecords = mutableListOf<R>()
        val sliceRecords = mutableListOf<TimeDependencySlice<T>>()

        currentRecords.forEach { record ->
            if (record.period.startDate < newRecord.period.startDate) {
                val slice = TimeDependencySlice(
                    dataType = record.recordDataType,
                    opIdIn = opId,
                    period = HrPeriod(
                        startDate = record.period.startDate,
                        endDate = newRecord.period.startDate.minusDays(1L)
                    ),
                    data = record.recordData
                )
                sliceRecords.add(slice)
            }

            if (record.period.endDate > newRecord.period.endDate) {
                val slice = TimeDependencySlice(
                    dataType = record.recordDataType,
                    opIdIn = opId,
                    period = HrPeriod(
                        startDate = newRecord.period.endDate.plusDays(1L),
                        endDate = record.period.endDate
                    ),
                    data = record.recordData
                )
                sliceRecords.add(slice)
            }
        }

        return sliceRecords.map { func(it) }
    }

    fun <T, R> sliceRecords(
        opId: UUID,
        newRecord: TimeDependencyRecord<T>,
        currentRecords: List<TimeDependencyRecord<T>>,
        factory: TimeDependencyRecordFactory<T, R>
    ): List<R> {
        TODO()
//        val sliceRecords = mutableListOf<TimeDependencySlice<T>>()
//
//        currentRecords.forEach { record ->
//            if (record.period().startDate < newRecord.period().startDate) {
//                val slice = TimeDependencySlice(
//                    dataType = record.dataType(),
//                    opIdIn = opId,
//                    period = HrPeriod(
//                        startDate = record.period().startDate,
//                        endDate = newRecord.period().startDate.minusDays(1L)
//                    ),
//                    data = record.data()
//                )
//                sliceRecords.add(slice)
//            }
//
//            if (record.period().endDate > newRecord.period().endDate) {
//                val slice = TimeDependencySlice(
//                    dataType = record.dataType(),
//                    opIdIn = opId,
//                    period = HrPeriod(
//                        startDate = newRecord.period().endDate.plusDays(1L),
//                        endDate = record.period().endDate
//                    ),
//                    data = record.data()
//                )
//                sliceRecords.add(slice)
//            }
//        }
//
//        return sliceRecords.map { factory.record(opId, it) }
    }
}