package ru.carcatcherbot.service

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.User
import ru.carcatcherbot.domain.model.States
import ru.carcatcherbot.service.handlers.LogicContext

interface ReceiverService {
    fun execute(update: Update)
    fun setState(user: User, state: States)
}

@Service
class ReceiverServiceImpl(
    private val logicContext: LogicContext
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

    override fun setState(user: User, state: States) {
        logicContext.setState(user, state)
    }

    private fun process(message: Message) {
        logicContext.execute(message)
    }

    private fun process(callbackQuery: CallbackQuery) {
        logicContext.execute(callbackQuery)
    }
}
