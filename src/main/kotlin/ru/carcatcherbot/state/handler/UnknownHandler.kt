package ru.carcatcherbot.state.handler

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import ru.carcatcherbot.state.StateHandler
import ru.carcatcherbot.state.States

@Service(States.UNKNOWN_HANDLER)
class UnknownHandler : StateHandler {
    override fun handle(message: Message) {
        TODO("Not yet implemented")
    }
}
