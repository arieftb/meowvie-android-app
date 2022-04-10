package id.my.arieftb.core.domain.usecase.date

import kotlinx.coroutines.flow.Flow

interface GetCurrentDateTimeMillisUseCase {
    fun invoke(): Flow<Long?>
}