package com.github.johnnysc.practicetdd

interface Choice {
    fun init(mediator: Mediator, block: () -> Unit)
    fun isChosen() : Boolean
    fun chose()
    fun rollback()

    object Empty : Choice {
        override fun init(mediator: Mediator, block: () -> Unit) = Unit
        override fun isChosen(): Boolean = false
        override fun chose() = Unit
        override fun rollback() = Unit

    }
}