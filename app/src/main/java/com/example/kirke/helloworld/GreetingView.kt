package com.example.kirke.helloworld

import android.support.v4.content.ContextCompat
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView

class GreetingView(root: ViewGroup): AsyncView<String>() {
    private val message = root.findViewById<TextView>(R.id.message)
    private val spinner = root.findViewById<ProgressBar>(R.id.spinner)

    override fun render(model: String) {
        with (message) {
            setTextColor(ContextCompat.getColor(context, R.color.success))
            text = model
        }
    }

    override fun render(error: Throwable) {
        with (message) {
            setTextColor(ContextCompat.getColor(context, R.color.failure))
            text = error.message
        }
    }

    override fun renderLoading(loading: Boolean) {
        if (loading) {
            spinner.visibility = VISIBLE
            message.visibility = GONE
        } else {
            message.visibility = VISIBLE
            spinner.visibility = GONE
        }
    }
}
