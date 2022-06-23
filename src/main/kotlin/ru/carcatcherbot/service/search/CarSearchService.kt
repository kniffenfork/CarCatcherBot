package ru.carcatcherbot.service.search

import org.springframework.stereotype.Service
import ru.carcatcherbot.domain.model.CarSearch
import ru.carcatcherbot.domain.model.CarSearchStatus
import ru.carcatcherbot.domain.repository.CarSearchRepository
import ru.carcatcherbot.service.user.UserService

interface CarSearchService {
    fun addSearchParameters(userId: Long, params: CarSearchParams): CarSearch
    fun makeSearchActive(userId: Long)
    fun getActiveSearchesBy(userId: Long): List<CarSearch>
    fun getEditingSearchesBy(userId: Long): List<CarSearch>
}

@Service
class CarSearchServiceImpl(
    private val userService: UserService,
    private val carSearchRepository: CarSearchRepository
) : CarSearchService {
    override fun addSearchParameters(userId: Long, params: CarSearchParams): CarSearch {
        val nonActiveSearch = getEditingSearchesBy(userId).firstOrNull()
        return if (nonActiveSearch != null) {
            editSearch(params, nonActiveSearch)
        } else {
            createSearch(params, userId)
        }
    }

    private fun editSearch(params: CarSearchParams, existingSearch: CarSearch): CarSearch {
        return carSearchRepository.save(
            existingSearch.copy(
                mark = params.mark ?: existingSearch.mark,
                model = params.model ?: existingSearch.model,
                generation = params.generation ?: existingSearch.generation,
                city = params.city ?: existingSearch.city,
                region = params.region ?: existingSearch.region,
            )
        )
    }

    private fun createSearch(params: CarSearchParams, userId: Long): CarSearch {
        return carSearchRepository.save(
            CarSearch(
                mark = params.mark,
                model = params.model,
                generation = params.generation,
                city = params.city,
                region = params.region,
                user = userService.getBy(userId)
            )
        )
    }

    override fun makeSearchActive(userId: Long) {
        val nonActiveSearch = getEditingSearchesBy(userId).firstOrNull()
        if (nonActiveSearch != null) {
            carSearchRepository.save(nonActiveSearch.copy(status = CarSearchStatus.ACTIVE))
        }
    }

    override fun getEditingSearchesBy(userId: Long) = carSearchRepository.findAllByUserIdAndStatusEquals(userId, CarSearchStatus.EDITING)

    override fun getActiveSearchesBy(userId: Long): List<CarSearch> = carSearchRepository.findAllByUserIdAndStatusEquals(userId, CarSearchStatus.ACTIVE)
}
