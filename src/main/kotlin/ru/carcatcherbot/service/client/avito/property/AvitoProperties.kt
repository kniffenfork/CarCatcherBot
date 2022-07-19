package ru.carcatcherbot.service.client.avito.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "avito-api")
data class AvitoProperties(
    var url: String = "",
    var user: String = "",
    var token: String = ""
)
