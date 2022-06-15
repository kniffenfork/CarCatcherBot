package ru.carcatcherbot.bot

import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand
import org.telegram.telegrambots.meta.api.objects.Update
import ru.carcatcherbot.commands.NonCommand
import ru.carcatcherbot.properties.BotProperties
import javax.annotation.PostConstruct

@Component
class CarCatcherBot(
    private val botProperty: BotProperties,
    private val botCommands: List<IBotCommand>,
    private val nonCommand: NonCommand
) : TelegramLongPollingCommandBot() {
    @PostConstruct
    fun initCommands() {
        botCommands.forEach {
            register(it)
        }
    }

    override fun getBotToken(): String {
        return botProperty.token
    }

    override fun getBotUsername(): String {
        return botProperty.name
    }

    override fun processNonCommandUpdate(update: Update) {
        nonCommand.execute(update.message, this)
    }
}
