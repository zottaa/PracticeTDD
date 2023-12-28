package com.github.johnnysc.practicetdd

interface TicketFactory {

    fun ticket(number: Int): LotteryTicket

    class Base : TicketFactory {

        override fun ticket(number: Int): LotteryTicket {

            return if (isInvalidNumber(number)){
                LotteryTicket.Fake()
            } else {
                LotteryTicket.Base(number)
            }
        }

        private fun isInvalidNumber(number: Int): Boolean {

            val numberString = number.toString()
            return numberString.contains("-") ||
                    (numberString.replace("_", "").length % 2 != 0) ||
                    numberString.length > 8 || numberString.length < 2
        }
    }
}