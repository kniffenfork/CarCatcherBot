package ru.carcatcherbot.service

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import ru.carcatcherbot.state.MessageContext

interface ReceiverService {
    fun execute(update: Update)
    fun process(message: Message)
}

@Service
class ReceiverServiceImpl(
    private val userService: UserService,
    private val messageContext: MessageContext
) : ReceiverService {
    override fun execute(update: Update) {
        if (update.hasMessage()) {
            process(update.message)
        } else {
            throw IllegalStateException("Not yet supported")
        }
    }

    override fun process(message: Message) {
        messageContext.execute(userService.getUserStateBy(message.from.id), message)
    }
}
