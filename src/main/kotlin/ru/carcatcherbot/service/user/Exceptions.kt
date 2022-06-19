package ru.carcatcherbot.service.user

open class UserServiceException(override val message: String) : Exception(message)

class UserNotFoundException(userId: Long) : UserServiceException("User with id $userId not found")
