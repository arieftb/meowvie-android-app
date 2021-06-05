package id.my.arieftb.meowvie.domain.repo

interface LanguageRepository {
    suspend fun getLanguageCode(): String
}