package ru.carcatcherbot.domain.config

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.carcatcherbot.domain.property.Properties
import javax.sql.DataSource

@Configuration
class Config(private val properties: Properties) {
    @Bean
    fun getDatasource(): DataSource {
        return DataSourceBuilder
            .create()
            .driverClassName("org.postgresql.Driver")
            .url(properties.url)
            .username(properties.username)
            .password(properties.password)
            .build()
    }
}
