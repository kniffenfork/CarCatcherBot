package ru.carcatcherbot.service.handlers.callback

enum class Callbacks(val code: String, val message: String) {
    BACK_TO_MENU("cancel", "Вернуться в меню"),
    BACK_TO_SETTING_CITY("back to setting city", "Назад"),
    BACK_TO_SETTING_REGION("back to setting region", "Назад"),
    BACK_TO_SETTING_MODEL("back to setting car model", "Назад"),
    BACK_TO_SETTING_MARK("back to setting mark", "Назад"),
    START_SEARCH("start search", "Запустить поиск объявлений"),
    ADD_SEARCH("add search", "Добавить новый поиск"),
    DELETE_SEARCH("delete search", "Удалить поиск")
}
