package com.example.kirke.helloworld

sealed class Result<T> {
    class Success<T>(val data: T) : Result<T>()
    class Failure<T>(val error: Throwable) : Result<T>()
    class Loading<T> : Result<T>()
}

interface View<Model> {
    fun render(model: Model)
}

abstract class AsyncView<Model> : View<Model> {
    fun render(result: Result<Model>) {
        when (result) {
            is Result.Success -> render(result.data)
            is Result.Failure -> render(result.error)
        }

        renderLoading(result is Result.Loading)
    }

    abstract fun render(error: Throwable)
    abstract fun renderLoading(loading: Boolean)
}
