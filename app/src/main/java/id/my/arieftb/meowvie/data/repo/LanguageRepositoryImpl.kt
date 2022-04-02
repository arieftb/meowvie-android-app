package id.my.arieftb.meowvie.data.repo

import id.my.arieftb.meowvie.data.local.language.LanguageLocalDataSource
import id.my.arieftb.meowvie.domain.repo.LanguageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LanguageRepositoryImpl @Inject constructor(val local: LanguageLocalDataSource) :
    LanguageRepository {
    override fun getLanguageCode(): Flow<String> {
        return local.getLanguageCode()
    }
}