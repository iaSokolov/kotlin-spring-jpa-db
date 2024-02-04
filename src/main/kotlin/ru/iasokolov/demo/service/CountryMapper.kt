package ru.iasokolov.demo.service

import org.springframework.stereotype.Component
import ru.iasokolov.demo.dto.CountryDto
import ru.iasokolov.demo.entity.Country

@Component
class CountryMapper(
    override val dataType: Int = 1
) : Mapper {
    override fun toDto(model: Country): CountryDto {
        return CountryDto(
            id = model.id,
            dataType = model.dataType,
            name = model.name
        )
    }
}