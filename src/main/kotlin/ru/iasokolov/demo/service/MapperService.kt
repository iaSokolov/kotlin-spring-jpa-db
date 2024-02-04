package ru.iasokolov.demo.service

import org.springframework.stereotype.Service
import ru.iasokolov.demo.dto.CountryDto
import ru.iasokolov.demo.entity.Country

@Service
class MapperService(
    private val mapper: List<Mapper>
) {
    fun toDto(model: Country): CountryDto {
        val mapper = try {
            mapper.first { it.dataType == model.dataType }
        } catch (error: NoSuchElementException) {
            CountryMapper()
        }
        return mapper.toDto(model)
    }
}