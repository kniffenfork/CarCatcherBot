package ru.carcatcherbot.domain.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "chats")
data class TelegramChat(
    @Id
    @Column(name = "id")
    val id: Long = 0,

    @Column(name = "state_code")
    @Enumerated(EnumType.STRING)
    val state: States = States.UNKNOWN,

    @Column(name = "last_message_id")
    val lastMessageId: Long = 0,

    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER)
    val carSearches: List<CarSearch> = emptyList(),

    @ManyToOne
    val user: TelegramUser = TelegramUser()
)
