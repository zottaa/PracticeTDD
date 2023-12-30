package com.github.johnnysc.practicetdd

interface IsEmptyHandleUseCase<T : Any> {

    suspend fun handle(useCase: T) : MessageUI

    class Empty<T : Any> : IsEmptyHandleUseCase<T> {
        override suspend fun handle(useCase: T): MessageUI = MessageUI.Empty

    }

}