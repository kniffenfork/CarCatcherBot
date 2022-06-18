package ru.carcatcherbot.service.handlers

import org.telegram.telegrambots.meta.api.objects.Chat

interface Handler {
    fun isAvailableForStateOf(chat: Chat): Boolean
}
