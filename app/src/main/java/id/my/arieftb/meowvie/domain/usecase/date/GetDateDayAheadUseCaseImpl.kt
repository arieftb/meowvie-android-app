package id.my.arieftb.meowvie.domain.usecase.date

import id.my.arieftb.meowvie.domain.repo.DateRepository

class GetDateDayAheadUseCaseImpl constructor(private val repository: DateRepository) :
    GetDateDayAheadUseCase {
    override suspend fun invoke(format: String, dayInterval: Int): String? {
        return repository.getDateAhead(format, dayInterval)
    }
}