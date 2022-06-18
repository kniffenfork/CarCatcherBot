package ru.carcatcherbot.service.handlers.callback

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Chat
import ru.carcatcherbot.domain.model.States
import ru.carcatcherbot.service.handlers.CallbackHandler
import ru.carcatcherbot.service.logic.chat.ChatService

@Service
class BackToMenuHandler(
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val chatSevice: ChatService
) : CallbackHandler {
    override fun handle(callbackQuery: CallbackQuery) {
        // TODO : Not yet impl
    }

    override fun getCallbackCode() = Callbacks.CANCEL.code

    override fun isAvailableForStateOf(chat: Chat) = chatSevice.getStateOf(chat) == States.WAITING_FOR_MARK_INPUT
}
