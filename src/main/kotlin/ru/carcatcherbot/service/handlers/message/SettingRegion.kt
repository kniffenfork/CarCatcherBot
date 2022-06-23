package ru.carcatcherbot.service.handlers.message

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
class SettingRegion(
    private val userService: UserService,
    private val carSearchService: CarSearchService,
    private val applicationEventPublisher: ApplicationEventPublisher
) : MessageHandler, StateInitializer {
    override fun handle(message: Message) {
        carSearchService.addSearchParameters(message.from.id, CarSearchParams(region = message.text))
        applicationEventPublisher.publishEvent(SetStateEvent(message.from, States.WAITING_FOR_MARK_INPUT))
    }

    override fun init(user: User) {
        applicationEventPublisher.publishEvent(
            SendButtonMessage(
                user.id, "Введите регион, где искать объявления",
                listOf(
                    listOf(Callbacks.BACK_TO_MENU),
                    listOf(Callbacks.BACK_TO_SETTING_CITY)
                )
            )
        )
    }

    override fun isAvailableForStateOf(user: User) = userService.getStateOf(user) == States.WAITING_FOR_REGION_INPUT
}
