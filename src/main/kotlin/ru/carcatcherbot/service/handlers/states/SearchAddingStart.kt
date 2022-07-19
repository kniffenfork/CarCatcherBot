package ru.carcatcherbot.service.handlers.states

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.User
import ru.carcatcherbot.domain.model.States
import ru.carcatcherbot.service.events.SendMessageEvent
import ru.carcatcherbot.service.events.SetStateEvent
import ru.carcatcherbot.service.handlers.StateInitializer
import ru.carcatcherbot.service.property.UserProperties
import ru.carcatcherbot.service.search.CarSearchService
import ru.carcatcherbot.service.user.UserService

@Service
class SearchAddingStart(
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val userService: UserService,
    private val carSearchService: CarSearchService,
    private val userProperties: UserProperties
) : StateInitializer {
    override fun init(user: User) {
        if (isAvailableAddSearchBy(user)) {
            processAddingFirstSearch(user)
            applicationEventPublisher.publishEvent(SetStateEvent(user, States.WAITING_FOR_CITY_INPUT))
        } else {
            applicationEventPublisher.publishEvent(SendMessageEvent(user.id, "Слишком много поисков. Удали один из старых"))
            applicationEventPublisher.publishEvent(SetStateEvent(user, States.READY_TO_RECEIVE_ADS))
        }
    }

    private fun isAvailableAddSearchBy(user: User): Boolean {
        return carSearchService.getCountOfActiveSearchesBy(user.id) < userProperties.availableSearchesCount
    }

    private fun processAddingFirstSearch(user: User) {
        if (hasNoSearches(user))
            applicationEventPublisher.publishEvent(SendMessageEvent(user.id, "Давай добавим твой первый поиск :)"))
    }

    private fun hasNoSearches(user: User) = carSearchService.getActiveSearchesBy(user.id).isEmpty()

    override fun isAvailableForStateOf(user: User) = userService.getStateOf(user) == States.ADD_SEARCH_INIT
}
