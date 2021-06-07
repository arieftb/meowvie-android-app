package id.my.arieftb.meowvie.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.my.arieftb.meowvie.domain.repo.DateRepository
import id.my.arieftb.meowvie.domain.repo.LanguageRepository
import id.my.arieftb.meowvie.domain.repo.MovieRepository
import id.my.arieftb.meowvie.domain.repo.TvShowRepository
import id.my.arieftb.meowvie.domain.usecase.date.GetCurrentDateUseCase
import id.my.arieftb.meowvie.domain.usecase.date.GetCurrentDateUseCaseImpl
import id.my.arieftb.meowvie.domain.usecase.language.GetLanguageUseCase
import id.my.arieftb.meowvie.domain.usecase.language.GetLanguageUseCaseImpl
import id.my.arieftb.meowvie.domain.usecase.movies.GetMoviesHighlightUseCase
import id.my.arieftb.meowvie.domain.usecase.movies.GetMoviesHighlightUseCaseImpl
import id.my.arieftb.meowvie.domain.usecase.movies.GetMoviesUseCase
import id.my.arieftb.meowvie.domain.usecase.movies.GetMoviesUseCaseImpl
import id.my.arieftb.meowvie.domain.usecase.tv_shows.GetTvShowsHighlightUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.GetTvShowsHighlightUseCaseImpl
import id.my.arieftb.meowvie.domain.usecase.tv_shows.GetTvShowsUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.GetTvShowsUseCaseImpl
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
    fun provideGetCurrentDateUseCase(repository: DateRepository): GetCurrentDateUseCase =
        GetCurrentDateUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideGetMoviesUseCase(
        getCurrentDateUseCase: GetCurrentDateUseCase,
        getLanguageUseCase: GetLanguageUseCase,
        movieRepository: MovieRepository
    ): GetMoviesUseCase =
        GetMoviesUseCaseImpl(getCurrentDateUseCase, getLanguageUseCase, movieRepository)

    @Provides
    @Singleton
    fun provideGetMoviesHighlightUseCase(getMoviesUseCase: GetMoviesUseCase): GetMoviesHighlightUseCase =
        GetMoviesHighlightUseCaseImpl(getMoviesUseCase)

    @Provides
    @Singleton
    fun provideGetTvShowsUseCase(
        getCurrentDateUseCase: GetCurrentDateUseCase,
        getLanguageUseCase: GetLanguageUseCase,
        repository: TvShowRepository
    ): GetTvShowsUseCase =
        GetTvShowsUseCaseImpl(getCurrentDateUseCase, getLanguageUseCase, repository)

    @Provides
    @Singleton
    fun provideGetTvShowsHighlightUseCase(getTvShowsUseCase: GetTvShowsUseCase): GetTvShowsHighlightUseCase =
        GetTvShowsHighlightUseCaseImpl(getTvShowsUseCase)
}