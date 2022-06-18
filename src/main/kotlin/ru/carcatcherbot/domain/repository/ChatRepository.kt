package ru.carcatcherbot.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.carcatcherbot.domain.model.TelegramChat

interface ChatRepository : JpaRepository<TelegramChat, Long>
