package id.my.arieftb.meowvie.domain.usecase.language

import id.my.arieftb.meowvie.domain.repo.LanguageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetLanguageUseCaseImpl @Inject constructor(private val repository: LanguageRepository) :
    GetLanguageUseCase {
    override fun invoke(): Flow<String> {
        return repository.getLanguageCode().map { language ->
            if (language != "in") {
                "en-US"
            } else {
                "id-ID"
            }
        }
    }
}