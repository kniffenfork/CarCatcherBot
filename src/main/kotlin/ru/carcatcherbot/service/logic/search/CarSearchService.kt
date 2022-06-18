package ru.carcatcherbot.service.logic.search

import org.springframework.stereotype.Service
import ru.carcatcherbot.domain.model.CarSearch
import ru.carcatcherbot.domain.repository.CarSearchRepository

interface CarSearchService {
    fun addSearch(chatId: Long, params: CarSearchParams): CarSearch
}

@Service
class CarSearchServiceImpl(
    private val carSearchRepository: CarSearchRepository
) : CarSearchService {
    override fun addSearch(chatId: Long, params: CarSearchParams): CarSearch {
        val carSearch = CarSearch(
            mark = params.mark,
            model = params.model,
            generation = params.generation,
            city = params.city,
            region = params.region
        )
        return carSearchRepository.save(carSearch)
    }
}
