package id.my.arieftb.meowvie.data.local.language

import kotlinx.coroutines.flow.Flow

interface LanguageLocalDataSource {
    fun getLanguageCode(): Flow<String>
}