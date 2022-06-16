package ru.carcatcherbot.states

enum class States(val description: String) {
    START("Start the bot"),
    WAITING_FOR_REGION_INPUT("Waiting for user to enter the region of the search"),
    WAITING_FOR_CITY_INPUT("Waiting for user to enter the city of the search"),
    WAITING_FOR_MARK_INPUT("Waiting for the user to enter the car mark for the search"),
    WAITING_FOR_MODEL_INPUT("Waiting for the user to enter the car model for the search"),
    UNKNOWN("Unknown state");

    companion object {
        const val START_HANDLER = "START"
        const val SETTING_REGION_HANDLER = "WAITING_FOR_REGION_INPUT"
        const val SETTING_CITY_HANDLER = "WAITING_FOR_CITY_INPUT"
        const val SETTING_MARK_HANDLER = "WAITING_FOR_MARK_INPUT"
        const val SETTING_MODEL_HANDLER = "WAITING_FOR_MODEL_INPUT"
        const val UNKNOWN_HANDLER = "UNKNOWN"
    }
}
