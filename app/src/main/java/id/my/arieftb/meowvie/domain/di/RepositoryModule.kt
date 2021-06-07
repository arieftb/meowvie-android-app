package id.my.arieftb.meowvie.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.my.arieftb.meowvie.data.local.date.DateLocalDataSource
import id.my.arieftb.meowvie.data.local.language.LanguageLocalDataSource
import id.my.arieftb.meowvie.data.remote.movie.MovieRemoteDataSource
import id.my.arieftb.meowvie.data.repo.DateRepositoryImpl
import id.my.arieftb.meowvie.data.repo.LanguageRepositoryImpl
import id.my.arieftb.meowvie.data.repo.MovieRepositoryImpl
import id.my.arieftb.meowvie.domain.repo.DateRepository
import id.my.arieftb.meowvie.domain.repo.LanguageRepository
import id.my.arieftb.meowvie.domain.repo.MovieRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMovieRepository(remote: MovieRemoteDataSource): MovieRepository =
        MovieRepositoryImpl(remote)

    @Provides
    @Singleton
    fun provideLanguageRepository(local: LanguageLocalDataSource): LanguageRepository =
        LanguageRepositoryImpl(local)

    @Provides
    @Singleton
    fun provideDateRepository(local: DateLocalDataSource): DateRepository = DateRepositoryImpl(local)
}