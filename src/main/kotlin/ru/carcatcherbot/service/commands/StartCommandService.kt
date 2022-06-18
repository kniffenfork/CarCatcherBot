package ru.carcatcherbot.service.commands

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import ru.carcatcherbot.domain.model.CarSearch
import ru.carcatcherbot.domain.model.TelegramChat
import ru.carcatcherbot.domain.model.TelegramUser
import ru.carcatcherbot.service.events.Button
import ru.carcatcherbot.service.events.SendButtonMessage
import ru.carcatcherbot.service.events.SendMessageEvent
import ru.carcatcherbot.service.handlers.callback.Callbacks
import ru.carcatcherbot.service.logic.chat.ChatService
import ru.carcatcherbot.service.logic.user.UserService

interface StartCommandService {
    fun handle(user: User, chat: Chat)
}

@Service
class StartCommandServiceImpl(
    private val chatService: ChatService,
    private val userService: UserService,
    private val applicationEventPublisher: ApplicationEventPublisher
) : StartCommandService {
    override fun handle(user: User, chat: Chat) {
        val telegramChat: TelegramChat = createIfNotExists(user, chat).second
        sendMessage(chat.id, "Добро пожаловать в CarCatcherBot!")
        if (telegramChat.carSearches.isEmpty()) {
            sendMessage(chat.id, "Давайте настроим ваш первый поиск!")
            sendMessage(chat.id, "Введите ваш город")
        } else {
            sendMessage(chat.id, "Я так вижу ты здесь не в первый раз :)")
            sendSearches(chat.id, telegramChat.carSearches)
        }
    }

    @Transactional
    protected fun createIfNotExists(user: User, chat: Chat): Pair<TelegramUser, TelegramChat> {
        val telegramUser = userService.createIfNotExists(user)
        val telegramChat = chatService.createIfNotExists(chat, telegramUser)
        return Pair(telegramUser, telegramChat)
    }

    private fun sendMessage(chatId: Long, text: String) {
        applicationEventPublisher.publishEvent(SendMessageEvent(chatId, text))
    }

    private fun sendSearches(chatId: Long, searches: List<CarSearch>) {
        applicationEventPublisher.publishEvent(
            SendButtonMessage(
                chatId,
                """
                Текущие параметры поиска:
                """.trimIndent(),
                listOf(
                    listOf(Button(Callbacks.START_SEARCH.message, Callbacks.START_SEARCH)),
                    listOf(Button(Callbacks.ADD_SEARCH.message, Callbacks.ADD_SEARCH)),
                    listOf(Button(Callbacks.DELETE_SEARCH.message, Callbacks.DELETE_SEARCH))
                )
            )
        )
    }
}
