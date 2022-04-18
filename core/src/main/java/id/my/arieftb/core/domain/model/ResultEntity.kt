package id.my.arieftb.core.domain.model

sealed class ResultEntity<out T : Any> {
    class Success<out T : Any>(val data: T) : ResultEntity<T>()
    class Failure<out T : Any>(val exception: Exception) : ResultEntity<T>()
}
