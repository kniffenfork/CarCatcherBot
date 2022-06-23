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
class SettingMark(
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val userService: UserService,
    private val carSearchService: CarSearchService
) : MessageHandler, StateInitializer {
    override fun handle(message: Message) {
        carSearchService.addSearchParameters(message.from.id, CarSearchParams(mark = message.text))
        applicationEventPublisher.publishEvent(SetStateEvent(message.from, States.WAITING_FOR_MODEL_INPUT))
    }

    override fun init(user: User) {
        applicationEventPublisher.publishEvent(
            SendButtonMessage(
                user.id, "Введите марку автомобиля для поиска",
                listOf(
                    listOf(Callbacks.BACK_TO_MENU),
                    listOf(Callbacks.BACK_TO_SETTING_REGION)
                )
            )
        )
    }

    override fun isAvailableForStateOf(user: User) = userService.getStateOf(user) == States.WAITING_FOR_MARK_INPUT
}
