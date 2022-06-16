package ru.carcatcherbot.commands

import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender
import ru.carcatcherbot.domain.model.TelegramUser
import ru.carcatcherbot.domain.model.UserParams
import ru.carcatcherbot.domain.repository.UserNotFoundException
import ru.carcatcherbot.service.UserService
import ru.carcatcherbot.states.States

@Component
class StartCommand(
    private val userService: UserService
) : BotCommand(Commands.START.code, Commands.START.description) {

    override fun execute(absSender: AbsSender, user: User, chat: Chat, arguments: Array<out String>) {
        val telegramUser = createUserIfNotExists(user)
        sendGreetingsMessage(absSender, chat)
        userService.updateUserState(telegramUser, States.WAITING_FOR_MODEL_INPUT)
    }

    private fun sendGreetingsMessage(absSender: AbsSender, chat: Chat) {
        val message = "Добро пожаловать в бот CarCatcher! Давайте настроим поиск)"
        absSender.execute(SendMessage(chat.id.toString(), message))
    }

    private fun createUserIfNotExists(user: User): TelegramUser {
        return try {
            userService.getBy(user.id)
        } catch (err: UserNotFoundException) {
            val params = UserParams(
                id = user.id,
                username = user.userName,
                lastName = user.lastName,
                firstName = user.firstName,
                state = States.START
            )
            userService.create(params)
        }
    }
}
