package ru.carcatcherbot.domain.repository

open class PersistenceException(override val message: String) : Throwable(message = message)

class UserNotFoundException(id: Long) : PersistenceException(message = "There is no user with id $id in database")

class RequiredFieldMissedException(name: String) : PersistenceException(message = "Required field $name is missed")
