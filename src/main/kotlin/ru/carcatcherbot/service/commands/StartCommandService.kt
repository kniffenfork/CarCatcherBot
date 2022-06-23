package ru.carcatcherbot.service.commands

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.User
import ru.carcatcherbot.domain.model.States
import ru.carcatcherbot.service.events.SendMessageEvent
import ru.carcatcherbot.service.handlers.LogicContext
import ru.carcatcherbot.service.search.CarSearchService
import ru.carcatcherbot.service.user.UserService

interface StartCommandService {
    fun handle(user: User)
}

@Service
class StartCommandServiceImpl(
    private val userService: UserService,
    private val carSearchService: CarSearchService,
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val logicContext: LogicContext
) : StartCommandService {
    override fun handle(user: User) {
        userService.createIfNotExists(user)
        sendMessage(user.id, "Добро пожаловать в CarCatcherBot!")
        if (carSearchService.getActiveSearchesBy(user.id).isEmpty()) {
            logicContext.setState(user, States.SEARCH_ADD_INIT)
        } else {
            sendMessage(user.id, "Я так вижу ты здесь не в первый раз :)")
            logicContext.setState(user, States.READY_TO_RECEIVE_ADS)
        }
    }

    private fun sendMessage(userId: Long, text: String) {
        applicationEventPublisher.publishEvent(SendMessageEvent(userId, text))
    }
}
