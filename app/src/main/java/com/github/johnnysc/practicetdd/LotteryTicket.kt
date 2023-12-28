package com.github.johnnysc.practicetdd

interface LotteryTicket {

    fun isFake(): Boolean
    fun isWinner(): Boolean

    class Fake : LotteryTicket {

        override fun isFake(): Boolean = true

        override fun isWinner(): Boolean = false

    }

    class Base(private val number: Int) : LotteryTicket {
        override fun isFake(): Boolean = false

        override fun isWinner(): Boolean {
            val numberString = number.toString()
            val left = numberString.substring(0, numberString.length / 2)
            val right = numberString.substring(numberString.length / 2)

            return (
                    left.map {
                        Character.getNumericValue(it)
                    }.sum()
                            ==
                            right.map {
                                Character.getNumericValue(it)
                            }.sum()
                    )
        }
    }
}