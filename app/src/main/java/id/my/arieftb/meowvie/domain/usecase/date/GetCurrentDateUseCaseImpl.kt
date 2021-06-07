package id.my.arieftb.meowvie.domain.usecase.date

import id.my.arieftb.meowvie.domain.repo.DateRepository
import javax.inject.Inject

class GetCurrentDateUseCaseImpl @Inject constructor(private val repository: DateRepository) :
    GetCurrentDateUseCase {
    override suspend fun invoke(format: String): String? {
        return repository.getCurrentDate(format)
    }
}