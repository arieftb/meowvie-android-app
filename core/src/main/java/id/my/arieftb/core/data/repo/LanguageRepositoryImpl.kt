package id.my.arieftb.core.data.repo

import id.my.arieftb.core.data.local.language.LanguageLocalDataSource
import id.my.arieftb.core.domain.repo.LanguageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LanguageRepositoryImpl @Inject constructor(val local: LanguageLocalDataSource) :
    LanguageRepository {
    override fun getLanguageCode(): Flow<String> {
        return local.getLanguageCode()
    }
}