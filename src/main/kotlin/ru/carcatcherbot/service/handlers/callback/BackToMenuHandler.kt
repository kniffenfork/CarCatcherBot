package ru.carcatcherbot.service.handlers.callback

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.User
import ru.carcatcherbot.bot.commands.Commands
import ru.carcatcherbot.service.events.SendMessageEvent
import ru.carcatcherbot.service.UserService
import ru.carcatcherbot.service.handlers.CallbackHandler
import ru.carcatcherbot.domain.model.States

@Service
class BackToMenuHandler(
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val userService: UserService
) : CallbackHandler {
    override fun handle(callbackQuery: CallbackQuery) {
        applicationEventPublisher.publishEvent(SendMessageEvent(callbackQuery.message.chatId, "Ok, обратно"))
        userService.updateUserState(callbackQuery.message.from, States.START)
    }

    override fun getCallbackCode() = Commands.CANCEL.code

    override fun isAvailableForStateOf(user: User) = userService.getStateBy(user.id) == States.WAITING_FOR_MARK_INPUT
}
