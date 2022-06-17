package ru.carcatcherbot.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "db")
data class DbProperties(
    var url: String = "",
    var username: String = "",
    var password: String = ""
)
