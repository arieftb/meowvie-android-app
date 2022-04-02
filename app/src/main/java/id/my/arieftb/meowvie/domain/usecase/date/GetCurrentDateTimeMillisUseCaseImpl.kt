package id.my.arieftb.meowvie.domain.usecase.date

import id.my.arieftb.meowvie.domain.repo.DateRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentDateTimeMillisUseCaseImpl @Inject constructor(private val repository: DateRepository) :
    GetCurrentDateTimeMillisUseCase {
    override fun invoke(): Flow<Long?> {
        return repository.getCurrentDateTimeMillis()
    }
}