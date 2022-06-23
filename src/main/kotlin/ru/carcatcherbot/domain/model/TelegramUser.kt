package ru.carcatcherbot.domain.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
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

    @Column(name = "state_code")
    @Enumerated(EnumType.STRING)
    val state: States = States.UNKNOWN,

    @Column(name = "last_message_id")
    val lastMessageId: Long = 0,

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    val carSearches: List<CarSearch> = emptyList(),
)
