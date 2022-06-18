package ru.carcatcherbot.service.events

class SendButtonMessage(
    val chatId: Long,
    val text: String,
    val buttons: List<List<Button>>
)

data class Button(
    val text: String,
    val callbackData: String
)
