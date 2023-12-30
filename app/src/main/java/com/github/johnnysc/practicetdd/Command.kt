package com.github.johnnysc.practicetdd

interface Command<T : K, K : Any> {

    interface Check<T : K, K : Any> : Command<T, K> {

        fun canHandle(message: String) : Boolean
    }

    interface CheckAndHandle<T : K, K : Any> : IsEmptyHandleUseCase<K>, Check<T, K>

    abstract class Abstract<T : K, K : Any>(private val parser: Parser<K>) : CheckAndHandle<T, K> {

        private var handleUseCase : IsEmptyHandleUseCase<K> = IsEmptyHandleUseCase.Empty()

        override fun canHandle(message: String): Boolean {
            handleUseCase = parser.map(message)
            return message.isNotEmpty()
        }

        override suspend fun handle(useCase: K): MessageUI {
            return handleUseCase.handle(useCase)
        }
    }
}