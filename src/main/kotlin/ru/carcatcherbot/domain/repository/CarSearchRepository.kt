package ru.carcatcherbot.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.carcatcherbot.domain.model.CarSearch
import ru.carcatcherbot.domain.model.CarSearchStatus

interface CarSearchRepository : JpaRepository<CarSearch, Long> {
    fun findAllByUserIdAndStatusEquals(userId: Long, status: CarSearchStatus): List<CarSearch>
}
