package ru.carcatcherbot.states.handlers

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import ru.carcatcherbot.events.SendMessageEvent
import ru.carcatcherbot.states.StateHandler
import ru.carcatcherbot.states.States

@Service(States.START_HANDLER)
class StartingHandler(
    private val applicationEventPublisher: ApplicationEventPublisher
) : StateHandler {
    override fun handle(message: Message) {
        applicationEventPublisher.publishEvent(
            SendMessageEvent(chatId = message.chatId, text = "Введите команду /start для начала поиска")
        )
    }
}
