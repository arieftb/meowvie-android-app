package id.my.arieftb.meowvie.domain.usecase.date

import id.my.arieftb.meowvie.domain.repo.DateRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDateMonthAheadUseCaseImpl @Inject constructor(private val repository: DateRepository) :
    GetDateMonthAheadUseCase {
    override fun invoke(format: String, monthInterval: Int): Flow<String?> {
        return repository.getMonthAhead(format, monthInterval)
    }
}