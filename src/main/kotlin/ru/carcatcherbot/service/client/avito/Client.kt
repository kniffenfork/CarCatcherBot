package ru.carcatcherbot.service.client.avito

import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import ru.carcatcherbot.service.client.avito.dto.AdResponseDto
import ru.carcatcherbot.service.client.avito.property.AvitoProperties

interface Client {
    fun fetchAdds(model: String): AdResponseDto?
}

@Component
class ClientImpl(
    private val avitoProperties: AvitoProperties,
    private val restTemplate: RestTemplate
) : Client {
    override fun fetchAdds(model: String): AdResponseDto? {
        println(buildSearchUri(model))
        restTemplate.postForEntity(buildSearchUri(model), Object(), String::class.java).body
        return restTemplate.getForEntity(buildSearchUri(model), AdResponseDto::class.java).body
    }

    private fun buildSearchUri(model: String): String {
        return "${avitoProperties.url}?user=${avitoProperties.user}&token=${avitoProperties.token}&param[7614]=\"$model\"&limit=1"
    }
}
