package com.github.johnnysc.practicetdd

interface Count {

    fun increment(message: MessageUI) : MessageUI

    class Base(
        private var count: Int = 0
    ) : Count {

        override fun increment(message: MessageUI) : MessageUI {
            return message.copyWithId(count++)
        }

    }

}