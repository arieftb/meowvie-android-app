package id.my.arieftb.meowvie.domain.repo

import kotlinx.coroutines.flow.Flow

interface LanguageRepository {
    fun getLanguageCode(): Flow<String>
}