package id.my.arieftb.meowvie.domain.usecase.date

import kotlinx.coroutines.flow.Flow

interface GetCurrentDateTimeMillisUseCase {
    fun invoke(): Flow<Long?>
}