package id.my.arieftb.meowvie.domain.usecase.date

import id.my.arieftb.meowvie.domain.repo.DateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCurrentDateUseCaseImpl @Inject constructor(private val repository: DateRepository) :
    GetCurrentDateUseCase {
    override fun invoke(format: String): Flow<String?> {
        return repository.getCurrentDate(format).flowOn(Dispatchers.IO)
    }
}