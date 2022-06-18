package ru.carcatcherbot.service

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.User
import ru.carcatcherbot.domain.model.TelegramUser
import ru.carcatcherbot.domain.model.UserParams
import ru.carcatcherbot.domain.repository.UserRepository
import ru.carcatcherbot.domain.model.States

interface UserService {
    fun getBy(userId: Long): TelegramUser
    fun create(params: UserParams): TelegramUser
    fun updateUserState(user: User, states: States)

    fun getStateBy(userId: Long): States
}

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
) : UserService {
    override fun getBy(userId: Long): TelegramUser {
        return userRepository.findById(userId).orElseThrow { UserNotFoundException(userId) }
    }

    override fun create(params: UserParams): TelegramUser {
        val telegramUser = TelegramUser(
            id = params.id ?: throw RequiredFieldMissedException("id"),
            states = params.states ?: throw RequiredFieldMissedException("state"),
            firstName = params.firstName ?: "",
            lastName = params.lastName ?: "",
            username = params.username ?: throw RequiredFieldMissedException("username"),
        )
        return userRepository.save(telegramUser)
    }

    override fun updateUserState(user: User, states: States) {
        val telegramUser = getBy(user.id)
        userRepository.save(telegramUser.copy(states = states))
    }

    override fun getStateBy(userId: Long): States {
        return getBy(userId).states
    }
}
