package ru.carcatcherbot.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "avito-api")
data class AvitoApiProperties(
    var url: String = "",
    var user: String = "",
    var token: String = ""
)
