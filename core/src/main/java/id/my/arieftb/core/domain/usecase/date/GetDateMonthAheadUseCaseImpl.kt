package id.my.arieftb.core.domain.usecase.date

import id.my.arieftb.core.domain.repo.DateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetDateMonthAheadUseCaseImpl @Inject constructor(private val repository: DateRepository) :
    GetDateMonthAheadUseCase {
    override fun invoke(format: String, monthInterval: Int): Flow<String?> {
        return repository.getMonthAhead(format, monthInterval).flowOn(Dispatchers.IO)
    }
}