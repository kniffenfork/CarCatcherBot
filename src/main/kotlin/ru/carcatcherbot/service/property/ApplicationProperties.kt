package ru.carcatcherbot.service.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@ConfigurationProperties(prefix = "app")
@Configuration
data class ApplicationProperties(
    var user: UserProperties
)

@ConfigurationProperties(prefix = "user")
@Configuration
data class UserProperties(
    var availableSearchesCount: Long = 3
)
