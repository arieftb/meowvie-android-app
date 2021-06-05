package id.my.arieftb.meowvie.domain.usecase.language

interface GetLanguageUseCase {
    suspend fun invoke(): String
}