package ru.carcatcherbot.state

import org.telegram.telegrambots.meta.api.objects.Message

interface StateHandler {
    fun handle(message: Message)
}
