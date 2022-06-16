package ru.carcatcherbot.domain.model

import ru.carcatcherbot.states.States

data class UserParams(
    val id: Long?,
    val username: String?,
    val firstName: String?,
    val lastName: String?,
    val state: States?
)
