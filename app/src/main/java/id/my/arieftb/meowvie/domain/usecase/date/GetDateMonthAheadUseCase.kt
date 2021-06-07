package id.my.arieftb.meowvie.domain.usecase.date

interface GetDateMonthAheadUseCase {
    suspend fun invoke(format: String, monthInterval: Int): String?
}