package ru.carcatcherbot.commands

enum class Steps(val description: String) {
    START("Start the bot"), WAITING_FOR_SEARCH_PARAMS("Waiting for enter search"), UNKNOWN("Non existing step")
}
