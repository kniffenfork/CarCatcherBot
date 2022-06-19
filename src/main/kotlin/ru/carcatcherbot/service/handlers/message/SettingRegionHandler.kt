package ru.carcatcherbot.service.handlers.message

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.User
import ru.carcatcherbot.service.user.UserService
import ru.carcatcherbot.service.handlers.MessageHandler
import ru.carcatcherbot.domain.model.States

@Service
class SettingRegionHandler(
    private val userService: UserService
) : MessageHandler {
    override fun handle(message: Message) {
        TODO("Not yet implemented")
    }

    override fun isAvailableForStateOf(user: User) = userService.getStateOf(user) == States.WAITING_FOR_REGION_INPUT
}
