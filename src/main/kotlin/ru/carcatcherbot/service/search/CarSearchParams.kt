package ru.carcatcherbot.service.search

data class CarSearchParams(
    val mark: String = "",
    val model: String = "",
    val generation: String = "",
    val city: String = "",
    val region: String = ""
)