package com.example.kirke.helloworld

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_greeting.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val NAME = "name"
        const val MESSAGE = "message"
    }

    private lateinit var model: GreetingViewModel
    private lateinit var greetingView: GreetingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        savedInstanceState?.apply(::restoreState)

        greetingView = GreetingView(findViewById(R.id.greeting))
        model = ViewModelProviders.of(this).get(GreetingViewModel::class.java)

        model.data.observe(this, Observer { result ->
            result?.let(greetingView::render)
        })

        load_button.setOnClickListener {
            model.greet(edit_name.text.toString())
        }
    }

    private fun restoreState(state: Bundle) {
        with (state) {
            edit_name.setText(getString(NAME), TextView.BufferType.EDITABLE)
            hello_message.text = getString(MESSAGE)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.apply {
            putString(NAME, edit_name.text.toString())
            putString(MESSAGE, hello_message.text.toString())
        }

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState?.apply(this::restoreState)
    }
}
