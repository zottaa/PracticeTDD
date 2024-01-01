package com.github.johnnysc.practicetdd

interface PagingRepository {

    enum class Strategy {
        NEXT, PREVIOUS, INIT
    }

    fun messages(strategy: Strategy) : List<MessageDomain>

}