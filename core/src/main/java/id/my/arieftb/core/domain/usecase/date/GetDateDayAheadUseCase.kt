package id.my.arieftb.core.domain.usecase.date

import kotlinx.coroutines.flow.Flow

interface GetDateDayAheadUseCase {
    fun invoke(format: String, dayInterval: Int): Flow<String?>
}