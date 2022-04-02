package id.my.arieftb.meowvie.domain.usecase.date

import kotlinx.coroutines.flow.Flow

interface GetDateMonthAheadUseCase {
    fun invoke(format: String, monthInterval: Int): Flow<String?>
}