package id.my.arieftb.meowvie.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.my.arieftb.meowvie.domain.repo.*
import id.my.arieftb.meowvie.domain.usecase.date.*
import id.my.arieftb.meowvie.domain.usecase.language.GetLanguageUseCase
import id.my.arieftb.meowvie.domain.usecase.language.GetLanguageUseCaseImpl
import id.my.arieftb.meowvie.domain.usecase.movies.*
import id.my.arieftb.meowvie.domain.usecase.movies.detail.GetMovieDetailUseCase
import id.my.arieftb.meowvie.domain.usecase.movies.detail.GetMovieDetailUseCaseImpl
import id.my.arieftb.meowvie.domain.usecase.movies.highlight.GetMoviesHighlightUseCase
import id.my.arieftb.meowvie.domain.usecase.movies.highlight.GetMoviesHighlightUseCaseImpl
import id.my.arieftb.meowvie.domain.usecase.movies.popular.GetMoviesPopularHighlightUseCase
import id.my.arieftb.meowvie.domain.usecase.movies.popular.GetMoviesPopularHighlightUseCaseImpl
import id.my.arieftb.meowvie.domain.usecase.movies.popular.GetMoviesPopularUseCase
import id.my.arieftb.meowvie.domain.usecase.movies.popular.GetMoviesPopularUseCaseImpl
import id.my.arieftb.meowvie.domain.usecase.movies.upcoming.GetMoviesUpcomingHighlightUseCase
import id.my.arieftb.meowvie.domain.usecase.movies.upcoming.GetMoviesUpcomingHighlightUseCaseImpl
import id.my.arieftb.meowvie.domain.usecase.movies.upcoming.GetMoviesUpcomingUseCase
import id.my.arieftb.meowvie.domain.usecase.movies.upcoming.GetMoviesUpcomingUseCaseImpl
import id.my.arieftb.meowvie.domain.usecase.tv_shows.*
import id.my.arieftb.meowvie.domain.usecase.tv_shows.detail.GetTvShowDetailUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.detail.GetTvShowDetailUseCaseImpl
import id.my.arieftb.meowvie.domain.usecase.tv_shows.highlight.GetTvShowsHighlightUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.highlight.GetTvShowsHighlightUseCaseImpl
import id.my.arieftb.meowvie.domain.usecase.tv_shows.popular.GetTvShowsPopularHighlightUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.popular.GetTvShowsPopularHighlightUseCaseImpl
import id.my.arieftb.meowvie.domain.usecase.tv_shows.popular.GetTvShowsPopularUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.popular.GetTvShowsPopularUseCaseImpl
import id.my.arieftb.meowvie.domain.usecase.tv_shows.upcoming.GetTvShowsUpcomingHighlightImpl
import id.my.arieftb.meowvie.domain.usecase.tv_shows.upcoming.GetTvShowsUpcomingHighlightUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.upcoming.GetTvShowsUpcomingUseCase
import id.my.arieftb.meowvie.domain.usecase.tv_shows.upcoming.GetTvShowsUpcomingUseCaseImpl
import id.my.arieftb.meowvie.domain.usecase.watch_list.*
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
    fun provideGetCurrentDateTimeMillisUseCase(repository: DateRepository): GetCurrentDateTimeMillisUseCase =
        GetCurrentDateTimeMillisUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideGetDateMonthAheadUseCase(repository: DateRepository): GetDateMonthAheadUseCase =
        GetDateMonthAheadUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideGetDateDayAheadUseCase(repository: DateRepository): GetDateDayAheadUseCase =
        GetDateDayAheadUseCaseImpl(repository)

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
    fun provideGetMoviesUpcomingUseCase(
        getDateMonthAheadUseCase: GetDateMonthAheadUseCase,
        getDateDayAheadUseCase: GetDateDayAheadUseCase,
        getMoviesUseCase: GetMoviesUseCase
    ): GetMoviesUpcomingUseCase = GetMoviesUpcomingUseCaseImpl(
        getDateMonthAheadUseCase,
        getDateDayAheadUseCase,
        getMoviesUseCase
    )

    @Provides
    @Singleton
    fun provideGetMoviesUpcomingHighlightUseCase(
        getMoviesUpcomingUseCase: GetMoviesUpcomingUseCase
    ): GetMoviesUpcomingHighlightUseCase = GetMoviesUpcomingHighlightUseCaseImpl(
        getMoviesUpcomingUseCase
    )

    @Provides
    @Singleton
    fun provideGetMoviesPopularUseCase(
        getCurrentDateUseCase: GetCurrentDateUseCase,
        getMoviesUseCase: GetMoviesUseCase
    ): GetMoviesPopularUseCase =
        GetMoviesPopularUseCaseImpl(getCurrentDateUseCase, getMoviesUseCase)

    @Provides
    @Singleton
    fun provideGetMoviesPopularHighlightUseCase(
        getMoviesPopularUseCase: GetMoviesPopularUseCase
    ): GetMoviesPopularHighlightUseCase =
        GetMoviesPopularHighlightUseCaseImpl(getMoviesPopularUseCase)

    @Provides
    @Singleton
    fun provideGetMovieDetailUseCase(
        getLanguageUseCase: GetLanguageUseCase,
        repository: MovieRepository
    ): GetMovieDetailUseCase = GetMovieDetailUseCaseImpl(getLanguageUseCase, repository)

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

    @Provides
    @Singleton
    fun provideGetTvShowsUpcomingUseCase(
        getDateMonthAheadUseCase: GetDateMonthAheadUseCase,
        getDateDayAheadUseCase: GetDateDayAheadUseCase,
        getTvShowsUseCase: GetTvShowsUseCase
    ): GetTvShowsUpcomingUseCase = GetTvShowsUpcomingUseCaseImpl(
        getDateMonthAheadUseCase,
        getDateDayAheadUseCase,
        getTvShowsUseCase
    )

    @Provides
    @Singleton
    fun provideGetTvShowUpcomingHighlightUseCase(
        getTvShowsUpcomingUseCase: GetTvShowsUpcomingUseCase
    ): GetTvShowsUpcomingHighlightUseCase =
        GetTvShowsUpcomingHighlightImpl(getTvShowsUpcomingUseCase)

    @Provides
    @Singleton
    fun provideGetTvShowsPopularUseCase(
        getCurrentDateUseCase: GetCurrentDateUseCase,
        getTvShowsUseCase: GetTvShowsUseCase
    ): GetTvShowsPopularUseCase =
        GetTvShowsPopularUseCaseImpl(getCurrentDateUseCase, getTvShowsUseCase)

    @Provides
    @Singleton
    fun provideGetTvShowsPopularHighlightUseCase(
        getTvShowsPopularUseCase: GetTvShowsPopularUseCase
    ): GetTvShowsPopularHighlightUseCase =
        GetTvShowsPopularHighlightUseCaseImpl(getTvShowsPopularUseCase)

    @Provides
    @Singleton
    fun provideGetTvShowDetailUseCase(
        getLanguageUseCase: GetLanguageUseCase,
        repository: TvShowRepository
    ): GetTvShowDetailUseCase = GetTvShowDetailUseCaseImpl(getLanguageUseCase, repository)

    @Provides
    @Singleton
    fun provideSaveWatchListUseCase(
        repository: WatchListRepository,
        getCurrentDateTimeMillisUseCase: GetCurrentDateTimeMillisUseCase
    ): SaveWatchListUseCase = SaveWatchListUseCaseImpl(getCurrentDateTimeMillisUseCase, repository)

    @Provides
    @Singleton
    fun provideCheckWatchListUseCase(
        repository: WatchListRepository
    ): CheckWatchListUseCase = CheckWatchListUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideRemoveWatchListUseCase(
        repository: WatchListRepository
    ): RemoveWatchListUseCase = RemoveWatchListUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideGetWatchListAllUseCase(
        repository: WatchListRepository
    ): GetWatchListAllUseCase = GetWatchListAllUseCaseImpl(repository)
}