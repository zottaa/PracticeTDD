package com.github.johnnysc.practicetdd

interface Now {

    fun now() : Long

    class Base : Now {

        private var time = System.currentTimeMillis()

        override fun now(): Long {
            return time
        }

    }
}