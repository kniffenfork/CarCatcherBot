package ru.carcatcherbot.service.listener

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Lazy
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import ru.carcatcherbot.service.events.SendButtonMessage
import ru.carcatcherbot.service.events.SendMessageEvent
import ru.carcatcherbot.service.MessageService

@Component
class ApplicationListener(
    private val messageService: MessageService
) {
    inner class SendMessage {
        @EventListener
        fun onApplicationEvent(event: SendMessageEvent) {
            messageService.sendMessage(event.chatId, event.text)
        }
        @EventListener
        fun onApplicationEvent(event: SendButtonMessage) {
            messageService.sendMessage(event.chatId, event.text, event.buttons)
        }
    }

    @Bean
    @Lazy
    fun getSendMessage(): SendMessage = SendMessage()
}
