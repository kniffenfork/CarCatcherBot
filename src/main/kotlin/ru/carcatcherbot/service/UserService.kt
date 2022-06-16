package ru.carcatcherbot.service

import org.springframework.stereotype.Service
import ru.carcatcherbot.domain.model.TelegramUser
import ru.carcatcherbot.domain.model.UserParams
import ru.carcatcherbot.domain.repository.RequiredFieldMissedException
import ru.carcatcherbot.domain.repository.UserNotFoundException
import ru.carcatcherbot.domain.repository.UserRepository
import ru.carcatcherbot.states.States

interface UserService {
    fun getBy(userId: Long): TelegramUser
    fun create(params: UserParams): TelegramUser
    fun updateUserState(telegramUser: TelegramUser, state: States)

    fun getUserStateBy(userId: Long): States
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
            state = params.state ?: throw RequiredFieldMissedException("state"),
            firstName = params.firstName ?: "",
            lastName = params.lastName ?: "",
            username = params.username ?: throw RequiredFieldMissedException("username"),
        )
        return userRepository.save(telegramUser)
    }

    override fun updateUserState(telegramUser: TelegramUser, state: States) {
        userRepository.save(telegramUser.copy(state = state))
    }

    override fun getUserStateBy(userId: Long): States {
        return getBy(userId).state
    }
}
