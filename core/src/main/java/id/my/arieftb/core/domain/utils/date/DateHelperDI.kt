package id.my.arieftb.core.domain.utils.date

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DateHelperDI {
    @Provides
    @Singleton
    fun provideDateHelper(): DateHelper  = DateHelper.instance()!!
}