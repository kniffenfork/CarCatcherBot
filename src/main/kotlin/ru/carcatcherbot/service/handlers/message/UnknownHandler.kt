package ru.carcatcherbot.service.handlers.message

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.Message
import ru.carcatcherbot.domain.model.States
import ru.carcatcherbot.service.handlers.MessageHandler
import ru.carcatcherbot.service.logic.chat.ChatService

@Service
class UnknownHandler(
    private val chatService: ChatService
) : MessageHandler {
    override fun handle(message: Message) {
        TODO("Not yet implemented")
    }

    override fun isAvailableForStateOf(chat: Chat) = chatService.getStateOf(chat) == States.UNKNOWN
}
