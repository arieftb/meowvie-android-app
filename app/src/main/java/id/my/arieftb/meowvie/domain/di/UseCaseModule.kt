package id.my.arieftb.meowvie.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.my.arieftb.meowvie.domain.repo.LanguageRepository
import id.my.arieftb.meowvie.domain.repo.MovieRepository
import id.my.arieftb.meowvie.domain.usecase.language.GetLanguageUseCase
import id.my.arieftb.meowvie.domain.usecase.language.GetLanguageUseCaseImpl
import id.my.arieftb.meowvie.domain.usecase.movies.GetMoviesUseCase
import id.my.arieftb.meowvie.domain.usecase.movies.GetMoviesUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetLanguageUseCase(languageRepository: LanguageRepository): GetLanguageUseCase =
        GetLanguageUseCaseImpl(languageRepository)

    @Provides
    @Singleton
    fun provideGetMoviesUseCase(getLanguageUseCase: GetLanguageUseCase, movieRepository: MovieRepository): GetMoviesUseCase =
        GetMoviesUseCaseImpl(getLanguageUseCase, movieRepository)
}