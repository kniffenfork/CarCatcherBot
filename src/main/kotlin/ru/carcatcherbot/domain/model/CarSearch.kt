package ru.carcatcherbot.domain.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "car_searches")
data class CarSearch(
    @Id
    @Column(name = "id")
    val id: Long = 0,

    @Column(name = "mark")
    val mark: String = "",

    @Column(name = "model")
    val model: String = "",

    @Column(name = "generation")
    val generation: String = "",

    @ManyToOne
    val telegramUser: TelegramUser
)
