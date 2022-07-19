package ru.carcatcherbot.service

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.bots.AbsSender
import ru.carcatcherbot.service.handlers.callback.Callbacks

interface MessageService {
    fun sendMessage(chatId: Long, message: String)
    fun sendMessage(chatId: Long, message: String, buttons: List<List<Callbacks>>)
}

@Service
class MessageServiceImpl(
    private val absSender: AbsSender
) : MessageService {
    override fun sendMessage(chatId: Long, message: String) {
        absSender.execute(SendMessage(chatId.toString(), message))
    }

    override fun sendMessage(chatId: Long, message: String, buttons: List<List<Callbacks>>) {
        val inlineKeyboardMarkup = InlineKeyboardMarkup()
        inlineKeyboardMarkup.keyboard = buttons.map { row -> row.map { it.toInlineKeyboardButton() } }
        val sendMessage = SendMessage(chatId.toString(), message)
        sendMessage.replyMarkup = inlineKeyboardMarkup
        absSender.execute(sendMessage)
    }

    private fun Callbacks.toInlineKeyboardButton(): InlineKeyboardButton {
        val inlineKeyboardButton = InlineKeyboardButton()
        inlineKeyboardButton.text = this.message
        inlineKeyboardButton.callbackData = this.code
        return inlineKeyboardButton
    }
}
