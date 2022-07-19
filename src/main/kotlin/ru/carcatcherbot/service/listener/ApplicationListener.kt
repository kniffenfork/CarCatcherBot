package ru.carcatcherbot.service.listener

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Lazy
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import ru.carcatcherbot.service.MessageService
import ru.carcatcherbot.service.ReceiverService
import ru.carcatcherbot.service.events.SendButtonMessage
import ru.carcatcherbot.service.events.SendMessageEvent
import ru.carcatcherbot.service.events.SetStateEvent

@Component
class ApplicationListener(
    private val messageService: MessageService,
    private val receiverService: ReceiverService
) {
    inner class SendMessage {
        @EventListener
        fun onApplicationEvent(event: SendMessageEvent) {
            messageService.sendMessage(event.userId, event.text)
        }
        @EventListener
        fun onApplicationEvent(event: SendButtonMessage) {
            messageService.sendMessage(event.chatId, event.text, event.buttons)
        }
    }

    inner class SetState {
        @EventListener
        fun onApplicationEvent(event: SetStateEvent) {
            receiverService.setState(event.user, event.state)
        }
    }

    @Bean
    @Lazy
    fun getSetState(): SetState = SetState()
    @Bean
    @Lazy
    fun getSendMessage(): SendMessage = SendMessage()
}
