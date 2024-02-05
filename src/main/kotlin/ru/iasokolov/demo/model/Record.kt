package ru.iasokolov.demo.model

interface Record<T> {
    val recordDataType: Int
    val recordOperationIn: Operation
    var recordOperationOut: Operation?
    var recordDeleted: Boolean
    val recordData: T
}