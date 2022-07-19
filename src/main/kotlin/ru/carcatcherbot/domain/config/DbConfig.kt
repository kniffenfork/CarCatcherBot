package ru.carcatcherbot.domain.config

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.carcatcherbot.domain.property.DbProperties
import javax.sql.DataSource

@Configuration
class DbConfig(private val dbProperties: DbProperties) {
    @Bean
    fun getDatasource(): DataSource {
        return DataSourceBuilder
            .create()
            .driverClassName("org.postgresql.Driver")
            .url(dbProperties.url)
            .username(dbProperties.username)
            .password(dbProperties.password)
            .build()
    }
}
