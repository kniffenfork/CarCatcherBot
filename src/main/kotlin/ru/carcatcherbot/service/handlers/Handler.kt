package ru.carcatcherbot.service.handlers

import org.telegram.telegrambots.meta.api.objects.User

interface Handler {
    fun isAvailableForStateOf(user: User): Boolean
}
