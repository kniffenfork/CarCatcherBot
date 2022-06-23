package ru.carcatcherbot.service.search

data class CarSearchParams(
    val mark: String? = null,
    val model: String? = null,
    val generation: String? = null,
    val city: String? = null,
    val region: String? = null
)
