package ru.iasokolov.demo.service

import org.springframework.stereotype.Component
import ru.iasokolov.demo.dto.CountryLangDto
import ru.iasokolov.demo.entity.Country

@Component
class CountryLangMapper(
    override val dataType: Int = 2
) : Mapper {
    override fun toDto(model: Country): CountryLangDto {
        return CountryLangDto(
            id = model.id,
            dataType = model.dataType,
            name = model.name,
            lang = model.lang
        )
    }
}