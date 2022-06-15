package ru.carcatcherbot.service

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import ru.carcatcherbot.bot.CarCatcherBot

interface MessageService {
    fun sendMessage(chatId: Long, message: String, bot: CarCatcherBot)
}

@Service
class MessageServiceImpl : MessageService {
    override fun sendMessage(chatId: Long, message: String, bot: CarCatcherBot) {
        bot.execute(SendMessage(chatId.toString(), message))
    }
}
