package com.github.johnnysc.practicetdd

interface GoodFilter : Good.Mapper<Boolean>, Change {

    fun isChosen(): Boolean

    abstract class Abstract : GoodFilter {

        private var isChosen: Boolean = false

        override fun change(filter: GoodFilter) {
            isChosen = isChosen.not()
        }

        override fun isChosen(): Boolean = isChosen
    }
}