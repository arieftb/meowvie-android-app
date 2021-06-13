package id.my.arieftb.meowvie.domain.usecase.date

interface GetCurrentDateTimeMillisUseCase {
    suspend fun invoke(): Long?
}