package ru.iasokolov.demo.dto

class CountryLangDto(
    id: Int,
    dataType: Int,
    name: String,
    val lang: String
): CountryDto(
    id, dataType, name
)