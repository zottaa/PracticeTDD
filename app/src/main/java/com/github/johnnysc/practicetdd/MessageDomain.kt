package com.github.johnnysc.practicetdd

interface MessageDomain {

    fun toUi() : MessageUi

    object LoadPrevious : MessageDomain {
        override fun toUi(): MessageUi = MessageUi.LoadPrevious
    }

    object LoadMore : MessageDomain {
        override fun toUi(): MessageUi = MessageUi.LoadMore
    }

    data class Base(
        private val id: Int,
        private val text: String
    ) : MessageDomain {
        override fun toUi(): MessageUi = MessageUi.Base(id, text)
    }

}