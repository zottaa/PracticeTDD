package com.github.johnnysc.practicetdd

interface Mediator {
    fun change(choice: Choice, block: () -> Unit)

    class Base : Mediator {
        private var currentChoice: Choice = Choice.Empty

        override fun change(choice: Choice, block: () -> Unit) {
            currentChoice.rollback()
            choice.chose()
            currentChoice = choice
            block.invoke()
        }
    }
}