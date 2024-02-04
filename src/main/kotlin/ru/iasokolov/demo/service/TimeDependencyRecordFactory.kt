package ru.iasokolov.demo.service

import ru.iasokolov.demo.model.TimeDependencyRecordSlice

interface TimeDependencyRecordFactory<T, R> {
    fun record(slice: TimeDependencyRecordSlice<T>): R
}