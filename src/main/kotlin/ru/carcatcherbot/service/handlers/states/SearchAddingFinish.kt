package ru.carcatcherbot.service.handlers.states

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.User
import ru.carcatcherbot.domain.model.States
import ru.carcatcherbot.service.events.SetStateEvent
import ru.carcatcherbot.service.handlers.StateInitializer
import ru.carcatcherbot.service.search.CarSearchService
import ru.carcatcherbot.service.user.UserService

@Service
class SearchAddingFinish(
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val userService: UserService,
    private val carSearchService: CarSearchService
) : StateInitializer {
    override fun init(user: User) {
        carSearchService.makeSearchActive(user.id)
        applicationEventPublisher.publishEvent(SetStateEvent(user, States.READY_TO_RECEIVE_ADS))
    }

    override fun isAvailableForStateOf(user: User) = userService.getStateOf(user) == States.ADD_SEARCH_FINISH
}
