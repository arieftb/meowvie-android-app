package id.my.arieftb.core.domain.usecase.language

import id.my.arieftb.core.domain.repo.LanguageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
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
        }.flowOn(Dispatchers.IO)
    }
}