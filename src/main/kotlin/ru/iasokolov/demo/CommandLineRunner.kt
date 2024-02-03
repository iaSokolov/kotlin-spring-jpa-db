package ru.iasokolov.demo

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class CommandLineRunner: CommandLineRunner {
    override fun run(vararg args: String?) {
        readln()
    }
}