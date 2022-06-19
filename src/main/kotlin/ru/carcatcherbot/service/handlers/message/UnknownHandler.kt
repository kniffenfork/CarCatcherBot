package ru.carcatcherbot.service.handlers.message

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.User
import ru.carcatcherbot.domain.model.States
import ru.carcatcherbot.service.handlers.MessageHandler
import ru.carcatcherbot.service.user.UserService

@Service
class UnknownHandler(
    private val userService: UserService
) : MessageHandler {
    override fun handle(message: Message) {
        TODO("Not yet implemented")
    }

    override fun isAvailableForStateOf(user: User) = userService.getStateOf(user) == States.UNKNOWN
}
