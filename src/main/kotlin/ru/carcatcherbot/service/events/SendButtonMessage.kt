package ru.carcatcherbot.service.events

import ru.carcatcherbot.service.handlers.callback.Callbacks

class SendButtonMessage(
    val chatId: Long,
    val text: String,
    val buttons: List<List<Callbacks>>
)
