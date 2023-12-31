package com.github.johnnysc.practicetdd

class MessagesArrayList : ArrayList<MessageUI>()

interface MessageUI {

    fun copyWithId(id: Int): MessageUI

    data class AiError(
        private val text: String,
        private val id: String = "0"
    ) : MessageUI {
        override fun copyWithId(id: Int): MessageUI =
            copy(text = text, id = id.toString())
    }

    data class Ai(
        private val text: String,
        private val id: String = "0"
    ) : MessageUI {
        override fun copyWithId(id: Int): MessageUI =
            copy(text = text, id = id.toString())
    }

    data class User(
        private val text: String,
        private val id: String = "0"
    ) : MessageUI {
        override fun copyWithId(id: Int): MessageUI =
            copy(text = text, id = id.toString())
    }

    data class Empty(private val text: String = "", private val id: String = "") : MessageUI {

        override fun copyWithId(id: Int) = copy(id = id.toString())
    }
}