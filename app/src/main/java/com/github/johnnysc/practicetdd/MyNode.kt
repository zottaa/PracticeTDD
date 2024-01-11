package com.github.johnnysc.practicetdd

interface MyNode {

    fun hasParent(): Boolean

    class Builder {
        private var idCount = 0
        private var value = ""
        private lateinit var parent: MyNode

        fun build(): MyNode {
            if (idCount == 0) {
                parent = Head(idCount++, value)
                return parent
            }
            parent = Base(idCount++, value, parent)
            return parent
        }

        fun value(value: String): Builder {
            this.value = value
            return this
        }
    }

    data class Head(
        private val id: Int,
        private val value: String
    ) : MyNode {
        override fun hasParent(): Boolean = false
    }

    data class Base(
        private val id: Int,
        private val value: String,
        private val parent: MyNode
    ) : MyNode {
        override fun hasParent(): Boolean = true
    }
}