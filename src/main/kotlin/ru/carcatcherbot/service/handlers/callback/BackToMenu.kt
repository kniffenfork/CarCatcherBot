package ru.carcatcherbot.service.handlers.callback

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.User
import ru.carcatcherbot.domain.model.States
import ru.carcatcherbot.service.events.SetStateEvent
import ru.carcatcherbot.service.handlers.CallbackHandler
import ru.carcatcherbot.service.search.CarSearchService
import ru.carcatcherbot.service.user.UserService

@Service
class BackToMenu(
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val userService: UserService,
    private val carSearchService: CarSearchService
) : CallbackHandler {
    override fun handle(callbackQuery: CallbackQuery) {
        carSearchService.deleteEditingSearches()
        applicationEventPublisher.publishEvent(SetStateEvent(callbackQuery.from, States.READY_TO_RECEIVE_ADS))
    }

    override fun getCallbackCode() = Callbacks.BACK_TO_MENU.code

    override fun isAvailableForStateOf(user: User) = userService.getStateOf(user) in States.searchConfiguringStates
}
