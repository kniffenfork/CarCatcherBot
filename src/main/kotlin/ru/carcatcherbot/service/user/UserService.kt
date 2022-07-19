package ru.carcatcherbot.service.user

import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.User
import ru.carcatcherbot.domain.model.CarSearch
import ru.carcatcherbot.domain.model.States
import ru.carcatcherbot.domain.model.TelegramUser
import ru.carcatcherbot.domain.repository.UserRepository

interface UserService {
    fun getBy(userId: Long): TelegramUser
    fun createIfNotExists(user: User): TelegramUser
    fun getStateOf(user: User): States
    fun setState(user: User, state: States): TelegramUser
    fun getSearchesOf(user: User): List<CarSearch>
}

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
) : UserService {
    override fun getBy(userId: Long): TelegramUser {
        return userRepository.findById(userId).orElseThrow { UserNotFoundException(userId) }
    }

    override fun createIfNotExists(user: User): TelegramUser {
        val existingUser = userRepository.findById(user.id).orElse(null)
        return if (existingUser == null) {
            create(user)
        } else {
            setState(user, States.START)
        }
    }

    override fun getStateOf(user: User) = getBy(user.id).state
    override fun setState(user: User, state: States) = userRepository.save(getBy(user.id).copy(state = state))
    override fun getSearchesOf(user: User) = getBy(user.id).carSearches

    private fun create(user: User): TelegramUser {
        val telegramUser = TelegramUser(
            id = user.id,
            firstName = user.firstName,
            lastName = user.lastName,
            username = user.userName,
            state = States.START
        )
        return userRepository.save(telegramUser)
    }
}
