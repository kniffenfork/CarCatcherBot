package ru.carcatcherbot.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "bot")
data class BotProperties(
    var name: String = "",
    var token: String = ""
)
