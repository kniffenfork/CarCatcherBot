package ru.carcatcherbot.service.handlers.states

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.User
import ru.carcatcherbot.domain.model.States
import ru.carcatcherbot.service.events.SendButtonMessage
import ru.carcatcherbot.service.events.SetStateEvent
import ru.carcatcherbot.service.handlers.MessageHandler
import ru.carcatcherbot.service.handlers.StateInitializer
import ru.carcatcherbot.service.handlers.callback.Callbacks
import ru.carcatcherbot.service.search.CarSearchParams
import ru.carcatcherbot.service.search.CarSearchService
import ru.carcatcherbot.service.user.UserService

@Service
class SettingCity(
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val userService: UserService,
    private val carSearchService: CarSearchService,
) : StateInitializer, MessageHandler {
    override fun handle(message: Message) {
        carSearchService.addSearchParameters(message.from.id, CarSearchParams(city = message.text))
        applicationEventPublisher.publishEvent(SetStateEvent(message.from, States.WAITING_FOR_REGION_INPUT))
    }

    override fun init(user: User) {
        applicationEventPublisher.publishEvent(
            SendButtonMessage(
                user.id, "Введите город, где искать объявления",
                listOf(
                    listOf(Callbacks.BACK_TO_MENU)
                )
            )
        )
    }

    override fun isAvailableForStateOf(user: User) = userService.getStateOf(user) == States.WAITING_FOR_CITY_INPUT
}
