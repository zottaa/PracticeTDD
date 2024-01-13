package com.github.johnnysc.practicetdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mediator = Mediator.Base()
        val block = {findViewById<Button>(R.id.saveButton).isEnabled = true}

        findViewById<ChoiceButton>(R.id.firstChoiceButton).init(mediator, block)
        findViewById<ChoiceButton>(R.id.secondChoiceButton).init(mediator, block)
        findViewById<ChoiceButton>(R.id.thirdChoiceButton).init(mediator, block)
    }
}