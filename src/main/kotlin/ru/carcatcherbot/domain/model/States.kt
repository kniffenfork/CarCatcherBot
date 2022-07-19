package ru.carcatcherbot.domain.model

enum class States(val description: String) {
    START("Start the bot"),
    ADD_SEARCH_INIT("Init state of adding user search"),
    WAITING_FOR_REGION_INPUT("Waiting for user to enter the region of the search"),
    WAITING_FOR_CITY_INPUT("Waiting for user to enter the city of the search"),
    WAITING_FOR_MARK_INPUT("Waiting for the user to enter the car mark for the search"),
    WAITING_FOR_MODEL_INPUT("Waiting for the user to enter the car model for the search"),
    ADD_SEARCH_FINISH("Saving created search"),
    READY_TO_RECEIVE_ADS("Message with settings"),
    RECEIVING_ADS("Service send to user ads via provided searches"),
    UNKNOWN("Unknown state");

    companion object {
        val searchConfiguringStates = listOf(
            WAITING_FOR_REGION_INPUT, WAITING_FOR_MODEL_INPUT, WAITING_FOR_CITY_INPUT, WAITING_FOR_MARK_INPUT
        )
    }
}
