package ru.carcatcherbot.service.handlers

import org.telegram.telegrambots.meta.api.objects.Message

interface MessageHandler : Handler {
    fun handle(message: Message)
}
