package com.github.johnnysc.practicetdd

import java.lang.IllegalStateException

interface Parser {

    fun parse(raw: String): List<Any>

    class Base(private val delimiter: String) : Parser {
        init {
            if (delimiter.isEmpty()) {
                throw IllegalStateException()
            }
        }

        override fun parse(raw: String): List<Any> {

            val parsedList = emptyList<Any>().toMutableList()

            raw.split(delimiter).forEach { item ->
                if (item.isNotEmpty()) {
                    parsedList.add(
                        ParserChainOfResponsibility(
                            ParserChainFragment.BooleanParser(item),
                            ParserChainOfResponsibility(
                                ParserChainFragment.NumberParser(item),
                                ParserChainOfResponsibility(
                                    ParserChainFragment.CharParser(item),
                                    ParserChainFragment.StringParser(item)
                                )
                            )
                        ).handle()
                    )
                }
            }

            return parsedList.toList()
        }
    }
}