package ru.carcatcherbot.service.handlers.callback

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.User
import ru.carcatcherbot.domain.model.States
import ru.carcatcherbot.service.handlers.CallbackHandler
import ru.carcatcherbot.service.user.UserService

@Service
class BackToMenuHandler(
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val userService: UserService
) : CallbackHandler {
    override fun handle(callbackQuery: CallbackQuery) {
        // TODO : Not yet impl
    }

    override fun getCallbackCode() = Callbacks.CANCEL.code

    override fun isAvailableForStateOf(user: User) = userService.getStateOf(user) == States.WAITING_FOR_MARK_INPUT
}
