package ru.carcatcherbot.commands

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Message
import ru.carcatcherbot.service.ReceiverService
@Component
class NonCommand(
    private val receiverService: ReceiverService
) {
    fun execute(message: Message) {
        receiverService.process(message)
    }
}
