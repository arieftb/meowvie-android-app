package id.my.arieftb.meowvie.data.local.language

import java.util.*

class LanguageLocalDataSourceImpl: LanguageLocalDataSource {
    override suspend fun getLanguageCode(): String {
        return Locale.getDefault().language
    }
}