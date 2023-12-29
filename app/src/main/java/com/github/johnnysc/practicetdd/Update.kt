package com.github.johnnysc.practicetdd

interface Update<ObjectType : Any> {
    fun update(argument: ObjectType)
}