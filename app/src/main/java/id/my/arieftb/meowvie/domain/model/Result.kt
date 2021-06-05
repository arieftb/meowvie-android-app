package id.my.arieftb.meowvie.domain.model

sealed class Result<out T : Any> {
    class Success<out T : Any>(val data: T) : Result<T>()
    class Failure<out T : Any>(val exception: Exception) : Result<T>()
}
