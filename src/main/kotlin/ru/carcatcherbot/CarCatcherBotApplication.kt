package ru.carcatcherbot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CarCatcherBotApplication

fun main(args: Array<String>) {
    runApplication<CarCatcherBotApplication>(*args)
}
