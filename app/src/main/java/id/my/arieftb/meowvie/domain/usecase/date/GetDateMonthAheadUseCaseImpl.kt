package id.my.arieftb.meowvie.domain.usecase.date

import id.my.arieftb.meowvie.domain.repo.DateRepository
import javax.inject.Inject

class GetDateMonthAheadUseCaseImpl @Inject constructor(private val repository: DateRepository): GetDateMonthAheadUseCase {
    override suspend fun invoke(format: String, monthInterval: Int): String? {
        return repository.getDateAhead(format, monthInterval)
    }
}