package ru.carcatcherbot.bot.commands

import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender
import ru.carcatcherbot.service.commands.StartCommandService

@Component
class StartCommand(
    private val startCommandService: StartCommandService
) : BotCommand(Commands.START.code, Commands.START.description) {

    override fun execute(absSender: AbsSender, user: User, chat: Chat, arguments: Array<out String>) {
        startCommandService.handle(user)
    }
}
