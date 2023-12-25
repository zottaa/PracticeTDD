package com.github.johnnysc.practicetdd

interface ParserChainFragment : ParserHandle {

    fun canHandle(): Boolean

    class BooleanParser(private val part: String) : ParserChainFragment {
        override fun canHandle(): Boolean {
            return part == "true" || part == "false"
        }

        override fun handle(): Any = part == "true"

    }

    class NumberParser(private val part: String) : ParserChainFragment {

        override fun canHandle(): Boolean =
            "-?[0-9]*(([.][0-9]+)?f?)?".toRegex().matches(part)

        override fun handle(): Any =
            PresentNumber.Base(part).number()
    }

    class CharParser(private val part: String) : ParserChainFragment {
        override fun canHandle(): Boolean = part.length == 1

        override fun handle(): Any = part[0]

    }

    class StringParser(private val part: String) : ParserChainFragment {
        override fun canHandle(): Boolean = true

        override fun handle(): Any = part

    }
}