package ru.carcatcherbot.service.client.avito.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class AdResponseDto(
    val code: Int,
    val data: List<AddDto>
)

data class AddDto(
    @JsonProperty("avitoid")
    val avitoId: String = "",

    @JsonProperty("url")
    val url: String = "",

    @JsonProperty("param_9690")
    val model: String = "",

    @JsonProperty("description")
    val description: String = ""
)
