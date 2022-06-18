package ru.carcatcherbot.domain.model

enum class States(val description: String) {
    START("Start the bot"),
    WAITING_FOR_REGION_INPUT("Waiting for user to enter the region of the search"),
    WAITING_FOR_CITY_INPUT("Waiting for user to enter the city of the search"),
    WAITING_FOR_MARK_INPUT("Waiting for the user to enter the car mark for the search"),
    WAITING_FOR_MODEL_INPUT("Waiting for the user to enter the car model for the search"),
    UNKNOWN("Unknown state");
}
