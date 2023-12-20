package com.github.johnnysc.practicetdd

interface MyStack<Type> {

    fun push(item: Type)

    fun pop(): Type

    abstract class Abstract<Type>(maxCount: Int) : MyStack<Type> {

        init {
            if (maxCount < 1) {
                throw IllegalStateException()
            }
        }

        protected var size: Int = 0
        protected val stack: Array<Any?> = Array(maxCount) { null }

    }


    class FIFO<Type>(private val maxCount: Int) : Abstract<Type>(maxCount) {

        override fun push(item: Type) {
            if (size == maxCount) {
                throw IllegalStateException("Stack overflow exception, maximum is $maxCount")
            }


            for (i in 0 until size) {
                stack[i + 1] = stack[i]
            }

            size += 1

            stack[0] = item as Any
        }

        override fun pop(): Type {
            if (size < 1) {
                throw IllegalStateException()
            }

            size--

            val item = stack[size]

            return item as Type
        }

    }

    class LIFO<Type>(private val maxCount: Int) : Abstract<Type>(maxCount) {

        override fun push(item: Type) {
            if (size == maxCount) {
                throw IllegalStateException("Stack overflow exception, maximum is $maxCount")
            }

            for (i in 0 until size) {
                stack[i + 1] = stack[i]
            }

            stack[size++] = item as Any
        }

        override fun pop(): Type {
            if (size < 1) {
                throw IllegalStateException()
            }

            size--

            val item = stack[size]

            return item as Type
        }

    }
}