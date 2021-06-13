package id.my.arieftb.meowvie.domain.usecase.date

import id.my.arieftb.meowvie.domain.repo.DateRepository
import javax.inject.Inject

class GetCurrentDateTimeMillisUseCaseImpl @Inject constructor(private val repository: DateRepository) :
    GetCurrentDateTimeMillisUseCase {
    override suspend fun invoke(): Long? {
        return repository.getCurrentDateTimeMillis()
    }
}