package ru.carcatcherbot.commands

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Message
import ru.carcatcherbot.bot.CarCatcherBot
import ru.carcatcherbot.client.AvitoClient
import ru.carcatcherbot.service.MessageService
import ru.carcatcherbot.service.UserService

@Component
class NonCommand(
    private val userService: UserService,
    private val messageService: MessageService,
    private val avitoClient: AvitoClient
) {
    fun execute(message: Message, bot: CarCatcherBot) {
        val user = message.from
        try {
            val telegramUser = userService.getBy(user.id)
            if (telegramUser.step == Steps.WAITING_FOR_SEARCH_PARAMS) {
                messageService.sendMessage(message.chatId, "Введенная марка: ${message.text}", bot)
                messageService.sendMessage(message.chatId, avitoClient.fetchAdds(message.text).toString(), bot)
            }
        } catch (err: Throwable) {
            throw err
        }
    }
}
