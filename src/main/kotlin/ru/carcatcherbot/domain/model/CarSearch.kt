package ru.carcatcherbot.domain.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
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
    val city: String? = null,

    @Column(name = "region")
    val region: String? = null,

    @Column(name = "mark")
    val mark: String? = null,

    @Column(name = "model")
    val model: String? = null,

    @Column(name = "generation")
    val generation: String? = null,

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    val status: CarSearchStatus = CarSearchStatus.EDITING,

    @ManyToOne
    val user: TelegramUser = TelegramUser()
) {
    override fun toString(): String {
        val resultString = StringBuilder()
        if (this.city != null) resultString.append("\t город: ${this.city}\n")
        if (this.region != null) resultString.append("\t регион: ${this.region}\n")
        if (this.mark != null) resultString.append("\t марка: ${this.mark}\n")
        if (this.model != null) resultString.append("\t модель: ${this.model}\n")
        return resultString.toString()
    }
}

enum class CarSearchStatus {
    EDITING, ACTIVE
}

fun buildSearchReport(searches: List<CarSearch>): String {
    val builder = StringBuilder()
    searches.forEachIndexed { index, carSearch -> builder.append("\n${index + 1}.\n$carSearch") }
    return builder.toString()
}
