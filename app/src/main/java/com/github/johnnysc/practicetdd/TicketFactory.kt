package com.github.johnnysc.practicetdd

interface TicketFactory {

    fun ticket(number: Int): LotteryTicket

    class Base : TicketFactory {

        override fun ticket(number: Int): LotteryTicket {

            if (isInvalidNumber(number)
            ) {
                return LotteryTicket.Fake
            }

            val cleanedNumberString = cleanNumber(number)

            val left = cleanedNumberString.take(cleanedNumberString.length / 2).map {
                Character.getNumericValue(it)
            }.sum()

            val right = cleanedNumberString.takeLast(cleanedNumberString.length / 2).map {
                Character.getNumericValue(it)
            }.sum()

            return if (left == right) {
                LotteryTicket.Win
            } else {
                LotteryTicket.Lose
            }
        }

        private fun cleanNumber(number: Int): String {
            return number.toString().replace("_", "").replace("-", "")
        }

        private fun isInvalidNumber(number: Int): Boolean {
            val numberString = number.toString()
            return numberString.contains("-") ||
                    (numberString.replace("_", "").length % 2 != 0) ||
                    numberString.length > 8
        }
    }
}