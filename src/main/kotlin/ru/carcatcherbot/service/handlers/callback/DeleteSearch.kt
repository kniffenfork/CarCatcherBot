package ru.carcatcherbot.service.handlers.callback

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.User
import ru.carcatcherbot.domain.model.States
import ru.carcatcherbot.service.events.SendButtonMessage
import ru.carcatcherbot.service.handlers.CallbackHandler
import ru.carcatcherbot.service.user.UserService

@Service
class DeleteSearch(
    private val userService: UserService,
    private val applicationEventPublisher: ApplicationEventPublisher
) : CallbackHandler {
    override fun handle(callbackQuery: CallbackQuery) {
        applicationEventPublisher.publishEvent(
            SendButtonMessage(
                callbackQuery.from.id, "Выберите номер поиска",
                listOf(
                    listOf(Callbacks.BACK_TO_MENU)
                )
            )
        )
        TODO("Not yet implemented")
    }

    override fun getCallbackCode() = Callbacks.DELETE_SEARCH.code

    override fun isAvailableForStateOf(user: User) = userService.getStateOf(user) == States.READY_TO_RECEIVE_ADS
}
