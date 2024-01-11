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

    class Functions : GoodCodeRule {
        override fun isValid(text: String): Boolean {
            val lines = text.lines()

            if (lines.count {
                    it.contains("fun")
                } > 5) {
                return false
            }

            for (line in lines) {
                if (line.contains("Any") && !line.contains("(): Any")) {
                    return false
                }
                if (line.contains("private fun")) {
                    return false
                }
                if (line.contains("abstract fun") && text.contains("abstract class") && !line.contains("protected abstract fun")) {
                    return false
                }
                if (line.contains("fun") && !line.contains("override") && !text.contains("interface") && !line.contains(
                        "protected"
                    ) && !line.contains("abstract class")
                ) {
                    return false
                }
                if (line.contains("fun") && !line.contains("override") && !text.contains("interface") && !text.contains("abstract class")) {
                    return false
                }
                if (line.contains("=") && text.contains("interface")) {
                    return false
                }
            }

            return true
        }
    }
}