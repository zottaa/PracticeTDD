package com.github.johnnysc.practicetdd

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan


interface MarkDown {
    interface Parser {
        fun parse(raw: String): ResultItem

        abstract class Abstract(
            private val color: String,
            private val mark: String
        ) : Parser {
            override fun parse(raw: String): ResultItem {
                val list = mutableListOf<ResultItem.StringAndIndex>()
                var text = raw
                var firstDelimiterStart = text.indexOf(mark, 0)
                var firstDelimiterEnd: Int
                var secondDelimiterStart: Int
                var secondDelimiterEnd: Int

                while (firstDelimiterStart != -1) {
                    firstDelimiterEnd = firstDelimiterStart + mark.length

                    secondDelimiterStart = text.indexOf(mark, firstDelimiterEnd)

                    if (secondDelimiterStart == -1) break

                    secondDelimiterEnd = secondDelimiterStart + mark.length

                    if (text.substring(firstDelimiterEnd, secondDelimiterStart).isNotEmpty())
                        list.add(
                            ResultItem.StringAndIndex(
                                text.substring(
                                    firstDelimiterEnd,
                                    secondDelimiterStart
                                ), firstDelimiterStart
                            )
                        )

                    text = text.removeRange(firstDelimiterStart, firstDelimiterEnd)
                        .removeRange(
                            secondDelimiterStart - mark.length,
                            secondDelimiterEnd - mark.length
                        )

                    firstDelimiterStart = text.indexOf(mark, secondDelimiterEnd - (mark.length * 2))
                }

                return ResultItem.Base(color, text, list)
            }
        }

        class Base(
            color: String,
            mark: String
        ) : Abstract(color, mark)


        class OneSignDelimiter(
            color: String,
            mark: Char
        ) : Abstract(color, mark.toString())
    }

    interface ResultItem {

        fun formattedText(): CharSequence
        data class Base(
            private val color: String,
            private val raw: String,
            private val list: List<StringAndIndex>
        ) : ResultItem {
            override fun formattedText(): CharSequence {
                val spannableString = SpannableString(raw)

                list.forEach {
                    it.setSpan(spannableString, color)
                }

                return spannableString
            }
        }

        data class StringAndIndex(
            private val string: String,
            private val index: Int
        ) {
            fun setSpan(spannableString: SpannableString, color: String) {
                spannableString.setSpan(
                    ForegroundColorSpan(Color.parseColor(color)),
                    index,
                    index + string.length,
                    Spannable.SPAN_MARK_MARK
                )
            }
        }
    }
}