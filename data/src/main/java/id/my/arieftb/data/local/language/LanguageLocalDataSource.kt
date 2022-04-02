package id.my.arieftb.data.local.language

import kotlinx.coroutines.flow.Flow

interface LanguageLocalDataSource {
    fun getLanguageCode(): Flow<String>
}