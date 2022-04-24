package id.my.arieftb.core.data.local.language

import kotlinx.coroutines.flow.Flow

interface LanguageLocalDataSource {
    fun getLanguageCode(): Flow<String>
}