package com.github.johnnysc.practicetdd

interface Update<T : CustomObject> {

    fun update(argument: T)

}