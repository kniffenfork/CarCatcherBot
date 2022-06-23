package ru.carcatcherbot.service.handlers.message

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.User
import ru.carcatcherbot.domain.model.States
import ru.carcatcherbot.domain.model.buildSearchReport
import ru.carcatcherbot.service.events.SendButtonMessage
import ru.carcatcherbot.service.handlers.MessageHandler
import ru.carcatcherbot.service.handlers.StateInitializer
import ru.carcatcherbot.service.handlers.callback.Callbacks
import ru.carcatcherbot.service.search.CarSearchService
import ru.carcatcherbot.service.user.UserService

@Service
class Menu(
    private val userService: UserService,
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val carSearchService: CarSearchService
) : MessageHandler, StateInitializer {
    override fun handle(message: Message) {
        TODO("Not yet implemented")
    }

    override fun init(user: User) {
        val searches = carSearchService.getActiveSearchesBy(user.id)
        applicationEventPublisher.publishEvent(
            SendButtonMessage(
                user.id,
                """
                Текущие параметры поиска:
                ${buildSearchReport(searches)}
                """.trimIndent(),
                listOf(
                    listOf(Callbacks.START_SEARCH),
                    listOf(Callbacks.ADD_SEARCH),
                )
            )
        )
    }

    override fun isAvailableForStateOf(user: User) = userService.getStateOf(user) == States.READY_TO_RECEIVE_ADS
}
