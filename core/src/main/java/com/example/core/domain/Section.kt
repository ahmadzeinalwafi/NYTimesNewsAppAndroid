package com.example.newsapp.core.domain.model

enum class Section(val apiValue: String, val displayName: String) {
    HOME("home", "Home"),
    ARTS("arts", "Arts"),
    SCIENCE("science", "Science"),
    US("us", "U.S."),
    WORLD("world", "World");

    // Helper to find enum by value
    companion object {
        fun fromValue(value: String): Section =
            entries.find { it.apiValue == value } ?: HOME
    }
}