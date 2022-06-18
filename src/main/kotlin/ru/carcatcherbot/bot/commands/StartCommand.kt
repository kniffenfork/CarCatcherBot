package ru.carcatcherbot.bot.commands

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender
import ru.carcatcherbot.domain.model.TelegramUser
import ru.carcatcherbot.domain.model.UserParams
import ru.carcatcherbot.service.UserNotFoundException
import ru.carcatcherbot.service.UserService
import ru.carcatcherbot.service.events.Button
import ru.carcatcherbot.service.events.SendButtonMessage
import ru.carcatcherbot.service.events.SendMessageEvent
import ru.carcatcherbot.domain.model.States

@Component
class StartCommand(
    private val userService: UserService,
    private val applicationEventPublisher: ApplicationEventPublisher
) : BotCommand(Commands.START.code, Commands.START.description) {

    override fun execute(absSender: AbsSender, user: User, chat: Chat, arguments: Array<out String>) {
        sendMessageToBot(chat, "Добро пожаловать в бот CarCatcher! Давайте настроим поиск)")
        applicationEventPublisher.publishEvent(
            SendButtonMessage(chatId = chat.id, text = "Выберите марку", listOf(listOf(Button("Назад", Commands.CANCEL.code))))
        )
        userService.updateUserState(user, States.WAITING_FOR_MARK_INPUT)
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
