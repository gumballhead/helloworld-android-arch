package com.example.kirke.helloworld

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class GreetingException(name: String): Exception("Go away, $name!")

object GreetingRepository {
    @Throws(GreetingException::class)
    suspend fun fetch(name: String): String {
        delay(200L)
        if (Math.random() < 0.5) throw GreetingException(name)
        return "Hello, $name!"
    }
}

class GreetingViewModel: ViewModel(), CoroutineScope {
    val data = MutableLiveData<Result<String>>()

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun greet(name: String) {
        data.value = Result.Loading()

        launch {
            try {
                val greeting = GreetingRepository.fetch(if (name.isBlank()) "World" else name)
                data.postValue(Result.Success(greeting))
            } catch (error: GreetingException) {
                data.postValue(Result.Failure(error))
            }
        }
    }

    override fun onCleared() {
        job.cancel()
    }
}
