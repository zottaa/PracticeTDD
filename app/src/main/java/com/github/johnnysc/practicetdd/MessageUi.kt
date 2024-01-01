package com.github.johnnysc.practicetdd

interface MessageUi {

    object LoadMore : MessageUi

    object LoadPrevious : MessageUi

    data class Base(
        private val id: Int,
        private val text: String
    ) : MessageUi



}