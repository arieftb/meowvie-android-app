package id.my.arieftb.meowvie.data.local.language

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

class LanguageLocalDataSourceImpl : LanguageLocalDataSource {
    override fun getLanguageCode(): Flow<String> {
        return flow {
            emit(Locale.getDefault().language)
        }
    }
}