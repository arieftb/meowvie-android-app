package id.my.arieftb.meowvie.domain.usecase.date

interface GetDateDayAheadUseCase {
    suspend fun invoke(format: String, dayInterval: Int): String?
}