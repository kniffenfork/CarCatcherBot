package ru.carcatcherbot.service.logic.chat

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Chat
import ru.carcatcherbot.domain.model.CarSearch
import ru.carcatcherbot.domain.model.States
import ru.carcatcherbot.domain.model.TelegramChat
import ru.carcatcherbot.domain.model.TelegramUser
import ru.carcatcherbot.domain.repository.ChatRepository

interface ChatService {
    fun createIfNotExists(chat: Chat, user: TelegramUser): TelegramChat
    fun getStateOf(chat: Chat): States
    fun setState(chat: Chat, state: States): TelegramChat
    fun getChatBy(chatId: Long): TelegramChat
    fun getSearchesOf(chat: Chat): List<CarSearch>
}

@Service
class ChatServiceImpl(
    private val chatRepository: ChatRepository
) : ChatService {
    override fun setState(chat: Chat, state: States) = chatRepository.save(getChatBy(chat.id).copy(state = state))

    override fun createIfNotExists(chat: Chat, user: TelegramUser): TelegramChat {
        val existingChat = chatRepository.findById(chat.id).orElse(null)
        return if (existingChat == null) {
            create(chat, user)
        } else {
            setState(chat, States.START)
        }
    }

    private fun create(chat: Chat, user: TelegramUser): TelegramChat {
        return chatRepository.save(TelegramChat(id = chat.id, state = States.START, user = user))
    }

    override fun getStateOf(chat: Chat) = getChatBy(chat.id).state

    override fun getChatBy(chatId: Long): TelegramChat = chatRepository.findById(chatId).orElseThrow { ChatNotFoundException(chatId) }

    override fun getSearchesOf(chat: Chat): List<CarSearch> = getChatBy(chat.id).carSearches
}
