package id.my.arieftb.meowvie.domain.usecase.date

import kotlinx.coroutines.flow.Flow

interface GetCurrentDateUseCase {
    fun invoke(format: String): Flow<String?>
}