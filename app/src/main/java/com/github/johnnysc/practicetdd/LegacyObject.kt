package com.github.johnnysc.practicetdd

data class LegacyObject(private val text: String, private val lambda: () -> Unit) {

    fun go() {
        lambda.invoke()
    }

}