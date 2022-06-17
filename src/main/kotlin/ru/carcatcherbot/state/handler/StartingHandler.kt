package ru.carcatcherbot.state.handler

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import ru.carcatcherbot.event.SendMessageEvent
import ru.carcatcherbot.state.StateHandler
import ru.carcatcherbot.state.States

@Service(States.STARTING_HANDLER)
class StartingHandler(
    private val applicationEventPublisher: ApplicationEventPublisher
) : StateHandler {
    override fun handle(message: Message) {
        applicationEventPublisher.publishEvent(
            SendMessageEvent(chatId = message.chatId, text = "Введите команду /start для начала поиска")
        )
    }
}
