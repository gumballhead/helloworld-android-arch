package com.example.kirke.helloworld

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

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

    override val coroutineContext = Job() + Dispatchers.IO

    fun greet(name: String) {
        data.value = Result.Loading()

        launch {
            try {
                val greeting = GreetingRepository.fetch(if (name.isNotBlank()) name else "World")
                data.postValue(Result.Success(greeting))
            }

            catch (error: GreetingException) {
                data.postValue(Result.Failure(error))
            }
        }
    }

    override fun onCleared() {
        coroutineContext.cancel()
    }
}
