package ru.carcatcherbot.service.logic.user

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.User
import ru.carcatcherbot.domain.model.TelegramUser
import ru.carcatcherbot.domain.repository.UserRepository

interface UserService {
    fun getBy(userId: Long): TelegramUser
    fun create(user: User): TelegramUser
    fun createIfNotExists(user: User): TelegramUser
}

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
) : UserService {
    override fun getBy(userId: Long): TelegramUser {
        return userRepository.findById(userId).orElseThrow { UserNotFoundException(userId) }
    }

    override fun createIfNotExists(user: User): TelegramUser {
        return userRepository.findById(user.id).orElse(null) ?: create(user)
    }

    override fun create(user: User): TelegramUser {
        val telegramUser = TelegramUser(
            id = user.id,
            firstName = user.firstName,
            lastName = user.lastName,
            username = user.userName,
        )
        return userRepository.save(telegramUser)
    }
}
