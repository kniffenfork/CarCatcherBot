package ru.carcatcherbot.domain.model

import ru.carcatcherbot.commands.Steps
import javax.persistence.*

@Entity
@Table(name = "users")
data class TelegramUser(
    @Id
    @Column(name = "id")
    val id: Long = 0,

    @Column(name = "step_code")
    @Enumerated(EnumType.STRING)
    val step: Steps = Steps.UNKNOWN,

    @Column(name = "username")
    val username: String = "",

    @Column(name = "first_name")
    val firstName: String = "",

    @Column(name = "last_name")
    val lastName: String = "",

    @OneToMany(mappedBy = "telegramUser")
    val carSearches: List<CarSearch> = emptyList()
)
