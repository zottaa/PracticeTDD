package com.github.johnnysc.practicetdd

interface Parser<T : Any> {

    fun map(data: String) : IsEmptyHandleUseCase<T>

}