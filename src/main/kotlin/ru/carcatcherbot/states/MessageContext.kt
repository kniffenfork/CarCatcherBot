package ru.carcatcherbot.states

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message

@Service
class MessageContext(
    private val stateHandlers: Map<String, StateHandler>
) {
    fun execute(state: States, message: Message) {
        val stateHandler = stateHandlers[state.name] ?: throw IllegalArgumentException("The state ${state.name} does not exist")
        stateHandler.handle(message)
    }
}
