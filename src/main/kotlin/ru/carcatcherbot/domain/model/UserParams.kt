package ru.carcatcherbot.domain.model

import ru.carcatcherbot.state.States

data class UserParams(
    val id: Long?,
    val username: String?,
    val firstName: String?,
    val lastName: String?,
    val states: States?
)
