package ru.carcatcherbot.service.events

import org.telegram.telegrambots.meta.api.objects.User
import ru.carcatcherbot.domain.model.States

data class SetStateEvent(
    val user: User,
    val state: States
)
