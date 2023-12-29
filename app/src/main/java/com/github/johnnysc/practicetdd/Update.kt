package com.github.johnnysc.practicetdd

interface Update<T : Any> {

    fun update(argument: T)

}