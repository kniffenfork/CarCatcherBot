package ru.carcatcherbot.service.handlers

import org.telegram.telegrambots.meta.api.objects.CallbackQuery

interface CallbackHandler : Handler {
    fun handle(callbackQuery: CallbackQuery)
    fun getCallbackCode(): String
}
