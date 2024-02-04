package ru.iasokolov.demo

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import ru.iasokolov.demo.entity.Country
import ru.iasokolov.demo.service.MapperService

@Component
class CommandLineRunner(
    private val mapperService: MapperService
) : CommandLineRunner {
    override fun run(vararg args: String?) {



    }


}
