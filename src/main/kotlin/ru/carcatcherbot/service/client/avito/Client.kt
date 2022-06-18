package ru.carcatcherbot.service.client.avito

import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import ru.carcatcherbot.service.client.avito.dto.AdResponseDto
import ru.carcatcherbot.service.client.avito.property.Properties

interface Client {
    fun fetchAdds(model: String): AdResponseDto?
}

@Component
class ClientImpl(
    private val properties: Properties,
    private val restTemplate: RestTemplate
) : Client {
    override fun fetchAdds(model: String): AdResponseDto? {
        println(buildSearchUri(model))
        return restTemplate.getForEntity(buildSearchUri(model), AdResponseDto::class.java).body
    }

    private fun buildSearchUri(model: String): String {
        return "${properties.url}?user=${properties.user}&token=${properties.token}&param[7614]=\"$model\"&limit=1"
    }
}
