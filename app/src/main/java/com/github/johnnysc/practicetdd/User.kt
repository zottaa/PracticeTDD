package com.github.johnnysc.practicetdd

interface User {
    fun receive(message: String)

    fun send(message: String)

    abstract class Abstract(
        private val mediator: Mediator
    ) : User {

        override fun send(message: String) {
            mediator.send(this, message)
        }
    }
}