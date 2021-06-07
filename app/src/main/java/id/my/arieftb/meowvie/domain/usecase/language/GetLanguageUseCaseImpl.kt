package id.my.arieftb.meowvie.domain.usecase.language

import id.my.arieftb.meowvie.domain.repo.LanguageRepository
import javax.inject.Inject

class GetLanguageUseCaseImpl @Inject constructor(private val repository: LanguageRepository): GetLanguageUseCase {
    override suspend fun invoke(): String {
        return if (repository.getLanguageCode() != "in") {
            "en-US"
        } else {
            "id-ID"
        }
    }
}