package ru.carcatcherbot.service

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.bots.AbsSender

interface MessageService {
    fun sendMessage(chatId: Long, message: String)
}

@Service
class MessageServiceImpl(
    private val absSender: AbsSender
) : MessageService {
    override fun sendMessage(chatId: Long, message: String) {
        absSender.execute(SendMessage(chatId.toString(), message))
    }
}
