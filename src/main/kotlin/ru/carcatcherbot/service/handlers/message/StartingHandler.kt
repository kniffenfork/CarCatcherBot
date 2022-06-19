package ru.carcatcherbot.service.handlers.message

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.User
import ru.carcatcherbot.domain.model.States
import ru.carcatcherbot.service.events.SendMessageEvent
import ru.carcatcherbot.service.handlers.MessageHandler
import ru.carcatcherbot.service.user.UserService

@Service
class StartingHandler(
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val userService: UserService
) : MessageHandler {
    override fun handle(message: Message) {
        applicationEventPublisher.publishEvent(
            SendMessageEvent(chatId = message.chatId, text = "Введите команду /start для начала поиска")
        )
    }

    override fun isAvailableForStateOf(user: User) = userService.getStateOf(user) == States.START
}
