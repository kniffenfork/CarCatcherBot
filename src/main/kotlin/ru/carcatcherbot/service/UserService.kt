package ru.carcatcherbot.service

import org.springframework.stereotype.Service
import ru.carcatcherbot.commands.Steps
import ru.carcatcherbot.domain.model.TelegramUser
import ru.carcatcherbot.domain.model.UserParams
import ru.carcatcherbot.domain.repository.RequiredFieldMissedException
import ru.carcatcherbot.domain.repository.UserNotFoundException
import ru.carcatcherbot.domain.repository.UserRepository

interface UserService {
    fun getBy(id: Long): TelegramUser
    fun create(params: UserParams): TelegramUser
    fun updateUserStep(telegramUser: TelegramUser, step: Steps)
}

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    override fun getBy(id: Long): TelegramUser {
        return userRepository.findById(id).orElseThrow { UserNotFoundException(id) }
    }

    override fun create(params: UserParams): TelegramUser {
        val telegramUser = TelegramUser(
            id = params.id ?: throw RequiredFieldMissedException("id"),
            step = params.step ?: throw RequiredFieldMissedException("step"),
            firstName = params.firstName ?: "",
            lastName = params.lastName ?: "",
            username = params.username ?: throw RequiredFieldMissedException("username"),
        )
        return userRepository.save(telegramUser)
    }

    override fun updateUserStep(telegramUser: TelegramUser, step: Steps) {
        userRepository.save(telegramUser.copy(step = step))
    }
}
