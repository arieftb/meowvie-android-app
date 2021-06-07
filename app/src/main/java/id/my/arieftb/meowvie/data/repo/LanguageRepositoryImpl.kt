package id.my.arieftb.meowvie.data.repo

import id.my.arieftb.meowvie.data.local.language.LanguageLocalDataSource
import id.my.arieftb.meowvie.domain.repo.LanguageRepository
import javax.inject.Inject

class LanguageRepositoryImpl @Inject constructor(val local: LanguageLocalDataSource) :
    LanguageRepository {
    override suspend fun getLanguageCode(): String {
        return local.getLanguageCode()
    }
}