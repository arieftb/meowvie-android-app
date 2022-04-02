package id.my.arieftb.meowvie.domain.usecase.language

import kotlinx.coroutines.flow.Flow

interface GetLanguageUseCase {
    fun invoke(): Flow<String>
}