package ru.carcatcherbot.service.handlers

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message

@Service
class LogicContext(
    private val messageHandlers: List<MessageHandler>,
    private val callbackHandlers: List<CallbackHandler>
) {
    fun execute(message: Message) {
        messageHandlers.filter { it.isAvailableForStateOf(message.from) }.forEach { it.handle(message) }
    }

    fun execute(callbackQuery: CallbackQuery) {
        callbackHandlers.filter { it.isAvailableForStateOf(callbackQuery.from) }
            .filter { it.getCallbackCode() == callbackQuery.data }
            .forEach { it.handle(callbackQuery) }
    }
}
