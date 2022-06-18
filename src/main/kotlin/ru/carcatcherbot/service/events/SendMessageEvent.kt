package ru.carcatcherbot.service.events

class SendMessageEvent(
    val chatId: Long,
    val text: String
)
