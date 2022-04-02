package id.my.arieftb.data.repo

import id.my.arieftb.data.local.language.LanguageLocalDataSource
import id.my.arieftb.meowvie.domain.repo.LanguageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LanguageRepositoryImpl @Inject constructor(val local: LanguageLocalDataSource) :
    LanguageRepository {
    override fun getLanguageCode(): Flow<String> {
        return local.getLanguageCode()
    }
}