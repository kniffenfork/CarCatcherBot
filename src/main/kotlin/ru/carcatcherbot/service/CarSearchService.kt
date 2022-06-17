package ru.carcatcherbot.service

import org.springframework.stereotype.Service
import ru.carcatcherbot.domain.model.CarSearchParams
import ru.carcatcherbot.domain.repository.CarSearchRepository

interface CarSearchService {
    fun addSearchToUser(params: CarSearchParams)
}

@Service
class CarSearchServiceImpl(
    private val carSearchRepository: CarSearchRepository
) : CarSearchService {
    override fun addSearchToUser(params: CarSearchParams) {
        TODO("Not yet implemented")
    }
}
