package id.my.arieftb.core.domain.usecase.date

import kotlinx.coroutines.flow.Flow

interface GetCurrentDateUseCase {
    fun invoke(format: String): Flow<String?>
}