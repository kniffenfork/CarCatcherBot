package ru.carcatcherbot.service.handlers.message

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.User
import ru.carcatcherbot.domain.model.States
import ru.carcatcherbot.service.events.SendMessageEvent
import ru.carcatcherbot.service.events.SetStateEvent
import ru.carcatcherbot.service.handlers.StateInitializer
import ru.carcatcherbot.service.search.CarSearchService
import ru.carcatcherbot.service.user.UserService

@Service
class SearchAdding(
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val userService: UserService,
    private val carSearchService: CarSearchService
) : StateInitializer {
    override fun init(user: User) {
        if (carSearchService.getActiveSearchesBy(user.id).isEmpty())
            applicationEventPublisher.publishEvent(SendMessageEvent(user.id, "Давай добавим твой первый поиск :)"))
        applicationEventPublisher.publishEvent(SetStateEvent(user, States.WAITING_FOR_CITY_INPUT))
    }

    override fun isAvailableForStateOf(user: User) = userService.getStateOf(user) == States.SEARCH_ADD_INIT
}
