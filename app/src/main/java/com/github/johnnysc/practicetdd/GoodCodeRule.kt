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

        private fun isVariableDeclaration(line: String): Boolean =
            line.contains("val ") || line.contains("var ")


        private fun isEncapsulated(line: String): Boolean =
            line.contains("private ") || line.contains("protected ")


    }

    class Inheritance : GoodCodeRule {

        override fun isValid(text: String): Boolean {
            val lines = text.lines()

            for (line in lines) {
                val trimmedLine = line.trim()

                if (trimmedLine.isEmpty()) {
                    continue
                }

                if (isNotAbstractClassDeclarationWithoutInheritance(trimmedLine) ||
                    isAbstractClassWithInheritanceFromAnotherClass(trimmedLine)
                ) {
                    return false
                }
            }

            return true
        }

        private fun isNotAbstractClassDeclarationWithoutInheritance(line: String): Boolean =
            line.contains("class ") &&
                    line.contains(": ").not() &&
                    line.contains("abstract ").not()


        private fun isAbstractClassWithInheritanceFromAnotherClass(line: String): Boolean =
            line.contains("abstract class ") &&
                    line.contains("(") &&
                    line.contains(")") &&
                    line.contains(": ") &&
                    line.contains("Activity ").not() &&
                    line.contains("Fragment ").not()


    }
}