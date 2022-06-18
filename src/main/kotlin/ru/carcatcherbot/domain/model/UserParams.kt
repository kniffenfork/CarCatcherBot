package ru.carcatcherbot.domain.model

data class UserParams(
    val id: Long?,
    val username: String?,
    val firstName: String?,
    val lastName: String?,
    val states: States?
)
