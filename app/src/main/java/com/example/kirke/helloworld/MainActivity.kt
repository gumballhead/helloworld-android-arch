package com.example.kirke.helloworld

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var greetingModel: GreetingViewModel
    private lateinit var greetingView: GreetingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        greetingView = GreetingView(findViewById(R.id.greeting))
        greetingModel = ViewModelProviders.of(this).get(GreetingViewModel::class.java)

        greetingModel.data.observe(this, Observer { result ->
            result?.let(greetingView::render)
        })

        greetButton.setOnClickListener {
            greetingModel.greet(editName.text.toString())
        }
    }
}
