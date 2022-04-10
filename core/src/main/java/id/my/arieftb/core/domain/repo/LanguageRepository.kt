package id.my.arieftb.core.domain.repo

import kotlinx.coroutines.flow.Flow

interface LanguageRepository {
    fun getLanguageCode(): Flow<String>
}