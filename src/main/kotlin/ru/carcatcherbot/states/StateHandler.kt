package ru.carcatcherbot.states

import org.telegram.telegrambots.meta.api.objects.Message

interface StateHandler {
    fun handle(message: Message)
}
