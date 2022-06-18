package ru.carcatcherbot.domain.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "users")
data class TelegramUser(
    @Id
    @Column(name = "id")
    val id: Long = 0,

    @Column(name = "username")
    val username: String = "",

    @Column(name = "first_name")
    val firstName: String = "",

    @Column(name = "last_name")
    val lastName: String = "",

    @OneToMany(mappedBy = "user")
    val chats: List<TelegramChat> = emptyList()
)
