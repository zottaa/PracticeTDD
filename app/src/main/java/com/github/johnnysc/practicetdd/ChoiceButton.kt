package com.github.johnnysc.practicetdd

import android.content.Context
import android.util.AttributeSet

class ChoiceButton
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatButton(context, attrs, defStyleAttr), Choice {
    override fun init(mediator: Mediator, block: () -> Unit) {
        setOnClickListener {
            mediator.change(this, block)
        }
    }

    override fun isChosen(): Boolean = isEnabled

    override fun chose() {
        isEnabled = false
    }

    override fun rollback() {
        isEnabled = true
    }
}