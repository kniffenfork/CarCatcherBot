package ru.carcatcherbot.state.handler

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import ru.carcatcherbot.state.StateHandler
import ru.carcatcherbot.state.States

@Service(States.SETTING_MARK_HANDLER)
class SettingMarkHandler : StateHandler {
    override fun handle(message: Message) {
        // TODO: FILL
    }
}
