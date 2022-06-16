package ru.carcatcherbot.service

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import ru.carcatcherbot.states.MessageContext

interface ReceiverService {
    fun process(message: Message)
}

@Service
class ReceiverServiceImpl(
    private val userService: UserService,
    private val messageContext: MessageContext
) : ReceiverService {
    override fun process(message: Message) {
        messageContext.execute(userService.getUserStateBy(message.from.id), message)
    }
}
