package ru.carcatcherbot.domain.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "db")
data class Properties(
    var url: String = "",
    var username: String = "",
    var password: String = ""
)
