package id.my.arieftb.meowvie.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.my.arieftb.meowvie.data.local.language.LanguageLocalDataSource
import id.my.arieftb.meowvie.data.remote.movie.MovieRemoteDataSource
import id.my.arieftb.meowvie.data.repo.LanguageRepositoryImpl
import id.my.arieftb.meowvie.data.repo.MovieRepositoryImpl
import id.my.arieftb.meowvie.domain.repo.LanguageRepository
import id.my.arieftb.meowvie.domain.repo.MovieRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideMovieRepository(remote: MovieRemoteDataSource): MovieRepository =
        MovieRepositoryImpl(remote)

    @Provides
    @Singleton
    fun provideLanguageRepository(local: LanguageLocalDataSource): LanguageRepository =
        LanguageRepositoryImpl(local)
}