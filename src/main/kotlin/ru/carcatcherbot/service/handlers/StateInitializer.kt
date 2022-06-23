package ru.carcatcherbot.service.handlers

import org.telegram.telegrambots.meta.api.objects.User

interface StateInitializer : Handler {
    fun init(user: User)
}
