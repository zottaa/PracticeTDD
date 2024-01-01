package com.github.johnnysc.practicetdd

interface GoodCodeRule {

    fun isValid(text: String): Boolean

    class Encapsulation : GoodCodeRule {
        override fun isValid(text: String): Boolean {
            val lines = text.lines()

            for (line in lines) {
                val trimmedLine = line.trim()

                if (trimmedLine.isEmpty()) {
                    continue
                }

                if (isVariableDeclaration(trimmedLine) && isEncapsulated(trimmedLine).not()) {
                    return false
                }
            }

            return true
        }

        private fun isVariableDeclaration(line: String): Boolean {
            return line.contains("val ") || line.contains("var ")
        }

        private fun isEncapsulated(line: String): Boolean {
            return line.contains("private ") || line.contains("protected ")
        }
    }
}