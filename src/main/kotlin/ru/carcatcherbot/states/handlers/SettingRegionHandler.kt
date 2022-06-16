package ru.carcatcherbot.states.handlers

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import ru.carcatcherbot.states.StateHandler
import ru.carcatcherbot.states.States

@Service(States.SETTING_REGION_HANDLER)
class SettingRegionHandler : StateHandler {
    override fun handle(message: Message) {
        TODO("Not yet implemented")
    }
}
