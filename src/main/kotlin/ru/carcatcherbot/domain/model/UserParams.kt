package ru.carcatcherbot.domain.model

import ru.carcatcherbot.commands.Steps

data class UserParams(
    val id: Long?,
    val username: String?,
    val firstName: String?,
    val lastName: String?,
    val step: Steps?
)
