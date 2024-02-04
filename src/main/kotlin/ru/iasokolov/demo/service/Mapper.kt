package ru.iasokolov.demo.service

import org.springframework.stereotype.Component
import ru.iasokolov.demo.dto.CountryDto
import ru.iasokolov.demo.entity.Country

@Component
interface Mapper {
    val dataType: Int

    fun toDto(model: Country): CountryDto
}
