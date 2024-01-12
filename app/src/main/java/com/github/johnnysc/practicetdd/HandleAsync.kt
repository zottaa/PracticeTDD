package com.github.johnnysc.practicetdd

interface HandleAsync {
    fun <T : Any> handleAsync(
        backgroundBlock: suspend () -> T,
        uiBlock: (T) -> Unit
    )
}