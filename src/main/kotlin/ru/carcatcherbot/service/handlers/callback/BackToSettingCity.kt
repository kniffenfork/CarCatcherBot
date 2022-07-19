package ru.carcatcherbot.service.handlers.callback

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.User
import ru.carcatcherbot.domain.model.States
import ru.carcatcherbot.service.events.SetStateEvent
import ru.carcatcherbot.service.handlers.CallbackHandler
import ru.carcatcherbot.service.user.UserService

@Service
class BackToSettingCity(
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val userService: UserService
) : CallbackHandler {
    override fun handle(callbackQuery: CallbackQuery) {
        applicationEventPublisher.publishEvent(SetStateEvent(callbackQuery.from, States.WAITING_FOR_CITY_INPUT))
    }

    override fun getCallbackCode() = Callbacks.BACK_TO_SETTING_CITY.code

    override fun isAvailableForStateOf(user: User) = userService.getStateOf(user) == States.WAITING_FOR_REGION_INPUT
}
