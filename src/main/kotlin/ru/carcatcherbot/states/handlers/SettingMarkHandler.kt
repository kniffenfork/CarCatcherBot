package ru.carcatcherbot.states.handlers

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import ru.carcatcherbot.states.StateHandler
import ru.carcatcherbot.states.States

@Service(States.SETTING_MARK_HANDLER)
class SettingMarkHandler : StateHandler {
    override fun handle(message: Message) {
        TODO("Not yet implemented")
    }
}
