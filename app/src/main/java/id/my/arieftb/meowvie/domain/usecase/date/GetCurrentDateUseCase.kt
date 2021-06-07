package id.my.arieftb.meowvie.domain.usecase.date

interface GetCurrentDateUseCase {
    suspend fun invoke(format: String): String?
}