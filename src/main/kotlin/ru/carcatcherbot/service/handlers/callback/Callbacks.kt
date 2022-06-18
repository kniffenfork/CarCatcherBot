package ru.carcatcherbot.service.handlers.callback

enum class Callbacks(val code: String, val message: String, val description: String) {
    CANCEL("cancel", "Вернуться в меню", "cancel command"),
    START_SEARCH("start search", "Запустить поиск объявлений", "starts searching ads"),
    ADD_SEARCH("add search", "Добавить новый поиск", "adds new search to chat"),
    DELETE_SEARCH("delete search", "Удалить поиск", "deletes existing search")
}
