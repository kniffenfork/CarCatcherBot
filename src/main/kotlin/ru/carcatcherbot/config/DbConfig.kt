package ru.carcatcherbot.config

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.carcatcherbot.property.DbProperties
import javax.sql.DataSource

@Configuration
class DbConfig(private val properties: DbProperties) {
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
