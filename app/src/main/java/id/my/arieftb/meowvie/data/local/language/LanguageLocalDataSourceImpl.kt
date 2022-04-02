package id.my.arieftb.meowvie.data.local.language

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*

class LanguageLocalDataSourceImpl : LanguageLocalDataSource {
    override fun getLanguageCode(): Flow<String> {
        return flow {
            emit(Locale.getDefault().language)
        }.flowOn(Dispatchers.IO)
    }
}