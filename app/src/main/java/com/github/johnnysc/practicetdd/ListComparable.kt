package com.github.johnnysc.practicetdd

interface ListComparable {
    fun areItemsTheSame(other: ListComparable): Boolean
    fun areContentsTheSame(other: ListComparable): Boolean

    class Base(
        private val id: String,
        private val name: String
    ) : ListComparable {
        override fun areItemsTheSame(other: ListComparable) =
            id == (other as Base).id

        override fun areContentsTheSame(other: ListComparable) =
            name == (other as Base).name
    }
}