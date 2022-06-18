package ru.carcatcherbot.service.logic.chat

open class ChatServiceException(override val message: String) : Exception(message)

class ChatNotFoundException(chatId: Long) : ChatServiceException("There is no chat with id $chatId")
