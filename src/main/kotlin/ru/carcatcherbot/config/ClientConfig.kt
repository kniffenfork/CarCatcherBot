package ru.carcatcherbot.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.web.client.RestTemplate

@Configuration
class ClientConfig {
    @Bean
    @Scope("prototype")
    fun getRestTemplate(): RestTemplate {
        return RestTemplate()
    }
}
