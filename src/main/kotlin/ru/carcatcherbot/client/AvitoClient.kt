package ru.carcatcherbot.client

import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import ru.carcatcherbot.dto.AvitoAdResponseDto
import ru.carcatcherbot.properties.AvitoApiProperties

interface AvitoClient {
    fun fetchAdds(model: String): AvitoAdResponseDto?
}

@Component
class AvitoClientImpl(
    private val properties: AvitoApiProperties,
    private val restTemplate: RestTemplate
) : AvitoClient {
    override fun fetchAdds(model: String): AvitoAdResponseDto? {
        println(buildSearchUri(model))
        return restTemplate.getForEntity(buildSearchUri(model), AvitoAdResponseDto::class.java).body
    }

    private fun buildSearchUri(model: String): String {
        return "${properties.url}?user=${properties.user}&token=${properties.token}&param[7614]=\"$model\"&limit=1"
    }
}
