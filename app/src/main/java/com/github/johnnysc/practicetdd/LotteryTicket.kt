package com.github.johnnysc.practicetdd

interface LotteryTicket {

    fun isFake() : Boolean
    fun isWinner() : Boolean

    object Fake : LotteryTicket {

        override fun isFake(): Boolean = true

        override fun isWinner(): Boolean = false

    }

    object Win : LotteryTicket {
        override fun isFake(): Boolean = false

        override fun isWinner(): Boolean = true

    }

    object Lose : LotteryTicket {
        override fun isFake(): Boolean = false

        override fun isWinner(): Boolean = false

    }
}