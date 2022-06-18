package ru.carcatcherbot.bot

import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand
import org.telegram.telegrambots.meta.api.objects.Update
import ru.carcatcherbot.bot.property.BotProperties
import ru.carcatcherbot.service.ReceiverService
import javax.annotation.PostConstruct

@Component
class CarCatcherBot(
    private val botProperty: BotProperties,
    private val botCommands: List<IBotCommand>,
    private val receiverService: ReceiverService
) : TelegramLongPollingCommandBot() {
    @PostConstruct
    fun initCommands() {
        botCommands.forEach {
            register(it)
        }
    }

    override fun getBotToken() = botProperty.token

    override fun getBotUsername() = botProperty.name

    override fun processNonCommandUpdate(update: Update) {
        receiverService.execute(update)
    }
}
