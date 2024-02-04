package ru.iasokolov.demo

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.iasokolov.demo.entity.Country
import ru.iasokolov.demo.service.MapperService
import ru.iasokolov.demo.dto.CountryDto
import ru.iasokolov.demo.dto.CountryLangDto
import ru.iasokolov.demo.entity.address.Address
import ru.iasokolov.demo.entity.address.AddressData
import ru.iasokolov.demo.entity.address.AddressFactory
import ru.iasokolov.demo.service.TimeDependencyRecordService
import java.time.LocalDate
import java.util.*


@SpringBootTest
class AppTests {
    @Autowired
    private lateinit var mapperService: MapperService

    @Test
    fun contextLoads() {
    }

    @Test
    fun countryDtoDataType() {
        val dto = mapperService.toDto(createCountry(1))

        assertTrue(dto is CountryDto)
    }

    @Test
    fun countryLangDtoDataType() {
        val dto = mapperService.toDto(createCountry(2))

        assertTrue(dto is CountryLangDto)
    }

    @Test
    fun countryCommonDtoDataType() {
        val dto = mapperService.toDto(createCountry(3))

        assertTrue(dto is CountryDto)
    }

    fun createCountry(dataType: Int): Country {
        return Country(
            id = 1,
            dataType = dataType,
            name = "",
            lang = "RU"
        )
    }
}

class AppSimpleTests {
    @Autowired
    private lateinit var timeDependencyRecordService: TimeDependencyRecordService

    @Test
    fun slicePeriodFactory() {
        val newAddress = Address(
            id = UUID.randomUUID(),
            startDate = LocalDate.of(2024, 1, 1),
            endDate = LocalDate.of(2024, 12, 31),
            dataType = 1,
            data = AddressData(
                fullAddress = "Новый адрес"
            )
        )

        val address = Address(
            id = UUID.randomUUID(),
            startDate = LocalDate.of(2000, 1, 1),
            endDate = LocalDate.of(9999, 12, 31),
            dataType = 1,
            data = AddressData(
                fullAddress = "Старый адрес"
            )
        )       

        val changeRecord: List<Address> = timeDependencyRecordService.sliceRecords(
            newRecord = newAddress,
            currentRecords = listOf(address),
            factory = AddressFactory()
        )

        assertEquals(2, changeRecord.size)
        assertNotNull(changeRecord.firstOrNull {
            it.startDate == address.startDate && it.endDate == newAddress.startDate.minusDays(1L)
        })
        assertNotNull(changeRecord.firstOrNull {
            it.startDate == newAddress.endDate.plusDays(1L) && it.endDate == address.endDate
        })
    }

    @Test
    fun slicePeriodFunction() {
        val newAddress = Address(
            id = UUID.randomUUID(),
            startDate = LocalDate.of(2024, 1, 1),
            endDate = LocalDate.of(2024, 12, 31),
            dataType = 1,
            data = AddressData(
                fullAddress = "Новый адрес"
            )
        )

        val address = Address(
            id = UUID.randomUUID(),
            startDate = LocalDate.of(2000, 1, 1),
            endDate = LocalDate.of(9999, 12, 31),
            dataType = 1,
            data = AddressData(
                fullAddress = "Старый адрес"
            )
        )

        val changeRecord = timeDependencyRecordService.sliceRecords(newAddress, listOf(address)) { slice ->
            Address(
                id = UUID.randomUUID(),
                startDate = slice.period.startDate,
                endDate = slice.period.endDate,
                dataType = 1,
                data = slice.data
            )
        }

        assertEquals(2, changeRecord.size)
        assertNotNull(changeRecord.firstOrNull {
            it.startDate == address.startDate && it.endDate == newAddress.startDate.minusDays(1L)
        })
        assertNotNull(changeRecord.firstOrNull {
            it.startDate == newAddress.endDate.plusDays(1L) && it.endDate == address.endDate
        })
    }
}