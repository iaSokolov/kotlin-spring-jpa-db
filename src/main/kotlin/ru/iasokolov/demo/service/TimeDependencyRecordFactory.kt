package ru.iasokolov.demo.service

import ru.iasokolov.demo.model.TimeDependencySlice

interface TimeDependencyRecordFactory<T, R> {
    fun record(slice: TimeDependencySlice<T>): R
}