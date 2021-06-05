package id.my.arieftb.meowvie.data.local.language

interface LanguageLocalDataSource {
    suspend fun getLanguageCode(): String
}