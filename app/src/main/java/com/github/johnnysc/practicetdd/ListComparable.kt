package com.github.johnnysc.practicetdd

interface ListComparable {
    fun areItemsTheSame(other: ListComparable): Boolean
    fun areContentsTheSame(other: ListComparable): Boolean

    fun <T : Any> compareIds(id: T): Boolean

    data class Base(
        private val id: String,
        private val name: String
    ) : ListComparable {
        override fun areItemsTheSame(other: ListComparable) = other.compareIds(id)

        override fun areContentsTheSame(other: ListComparable) = this == other

        override fun <T : Any> compareIds(id: T): Boolean =
            this.id == id
    }
}