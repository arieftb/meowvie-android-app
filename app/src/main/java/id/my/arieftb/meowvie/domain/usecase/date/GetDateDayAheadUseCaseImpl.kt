package id.my.arieftb.meowvie.domain.usecase.date

import id.my.arieftb.meowvie.domain.repo.DateRepository
import kotlinx.coroutines.flow.Flow

class GetDateDayAheadUseCaseImpl constructor(private val repository: DateRepository) :
    GetDateDayAheadUseCase {
    override fun invoke(format: String, dayInterval: Int): Flow<String?> {
        return repository.getDateAhead(format, dayInterval)
    }
}