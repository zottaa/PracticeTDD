package com.github.johnnysc.practicetdd

interface ParserHandle {

    fun handle(): Any
}

class ParserChainOfResponsibility(
    private val handle: ParserChainFragment,
    private val nextFragment: ParserHandle
) : ParserHandle {
    override fun handle(): Any =
        if (handle.canHandle()) {
            handle.handle()
        } else {
            nextFragment.handle()
        }
}