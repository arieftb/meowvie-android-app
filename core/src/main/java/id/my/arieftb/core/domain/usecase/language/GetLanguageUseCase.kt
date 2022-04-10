package id.my.arieftb.core.domain.usecase.language

import kotlinx.coroutines.flow.Flow

interface GetLanguageUseCase {
    fun invoke(): Flow<String>
}