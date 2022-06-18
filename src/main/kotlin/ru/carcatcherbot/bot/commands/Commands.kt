package ru.carcatcherbot.bot.commands

enum class Commands(val code: String, val description: String) {
    START("start", "Start work with bot"),
    CANCEL("cancel", "Back to the menu")
}
