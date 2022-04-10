package id.my.arieftb.core.domain.usecase.date

import id.my.arieftb.core.domain.repo.DateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetDateDayAheadUseCaseImpl constructor(private val repository: DateRepository) :
    GetDateDayAheadUseCase {
    override fun invoke(format: String, dayInterval: Int): Flow<String?> {
        return repository.getDateAhead(format, dayInterval).flowOn(Dispatchers.IO)
    }
}