package ru.carcatcherbot.service.handlers

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.User
import ru.carcatcherbot.domain.model.States
import ru.carcatcherbot.service.user.UserService

@Service
class LogicContext(
    private val messageHandlers: List<MessageHandler>,
    private val callbackHandlers: List<CallbackHandler>,
    private val stateInitializers: List<StateInitializer>,
    private val userService: UserService
) {
    fun execute(message: Message) {
        messageHandlers.filter { it.isAvailableForStateOf(message.from) }.forEach { it.handle(message) }
    }

    fun execute(callbackQuery: CallbackQuery) {
        callbackHandlers.filter { it.isAvailableForStateOf(callbackQuery.from) }
            .filter { it.getCallbackCode() == callbackQuery.data }
            .forEach { it.handle(callbackQuery) }
    }

    fun setState(user: User, state: States) {
        userService.setState(user, state)
        stateInitializers.filter { it.isAvailableForStateOf(user) }.forEach { it.init(user) }
    }
}
