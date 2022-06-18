package ru.carcatcherbot.service

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import ru.carcatcherbot.service.handlers.LogicContext

interface ReceiverService {
    fun execute(update: Update)
}

@Service
class ReceiverServiceImpl(
    private val messageContext: LogicContext
) : ReceiverService {
    override fun execute(update: Update) {
        if (update.hasMessage()) {
            process(update.message)
        } else if (update.hasCallbackQuery()) {
            process(update.callbackQuery)
        } else {
            throw IllegalStateException("Not yet supported")
        }
    }

    private fun process(message: Message) {
        messageContext.execute(message)
    }

    private fun process(callbackQuery: CallbackQuery) {
        messageContext.execute(callbackQuery)
    }
}
