package ru.carcatcherbot.domain.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "car_searches")
data class CarSearch(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "city")
    val city: String = "",

    @Column(name = "region")
    val region: String = "",

    @Column(name = "mark")
    val mark: String = "",

    @Column(name = "model")
    val model: String = "",

    @Column(name = "generation")
    val generation: String = "",

    @ManyToOne
    val chat: TelegramChat = TelegramChat()
)
