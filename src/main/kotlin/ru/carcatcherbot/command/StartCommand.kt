package ru.carcatcherbot.command

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender
import ru.carcatcherbot.domain.model.TelegramUser
import ru.carcatcherbot.domain.model.UserParams
import ru.carcatcherbot.domain.repository.UserNotFoundException
import ru.carcatcherbot.event.SendMessageEvent
import ru.carcatcherbot.service.UserService
import ru.carcatcherbot.state.States

@Component
class StartCommand(
    private val userService: UserService,
    private val applicationEventPublisher: ApplicationEventPublisher
) : BotCommand(Command.START.code, Command.START.description) {

    override fun execute(absSender: AbsSender, user: User, chat: Chat, arguments: Array<out String>) {
        val telegramUser = createUserIfNotExists(user)
        sendMessageToBot(chat, "Добро пожаловать в бот CarCatcher! Давайте настроим поиск)")
        sendMessageToBot(chat, "Введи марку, по которой собираешься вести поиск")
        userService.updateUserState(telegramUser, States.WAITING_FOR_MARK_INPUT)
    }

    private fun sendMessageToBot(chat: Chat, text: String) {
        applicationEventPublisher.publishEvent(
            SendMessageEvent(chatId = chat.id, text = text)
        )
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
                states = States.START
            )
            userService.create(params)
        }
    }
}
