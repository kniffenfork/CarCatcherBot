package ru.carcatcherbot.state

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message

@Service
class MessageContext(
    private val stateHandlers: Map<String, StateHandler>
) {
    fun execute(states: States, message: Message) {
        val stateHandler = stateHandlers[states.name] ?: throw IllegalArgumentException("The state ${states.name} does not exist")
        stateHandler.handle(message)
    }
}
