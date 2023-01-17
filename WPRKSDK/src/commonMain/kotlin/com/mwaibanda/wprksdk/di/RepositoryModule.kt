package com.mwaibanda.wprksdk.di

import com.mwaibanda.wprksdk.data.repository.AuthRepositoryImpl
import com.mwaibanda.wprksdk.data.repository.PodcastRepositoryImpl
import com.mwaibanda.wprksdk.data.repository.ShowsRepositoryImpl
import com.mwaibanda.wprksdk.main.repository.AuthRepository
import com.mwaibanda.wprksdk.main.repository.PodcastRepository
import com.mwaibanda.wprksdk.main.repository.ShowsRepository
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val RepositoryModule = DI.Module("SDK/Repository") {
    bind<PodcastRepository>() with singleton {
        PodcastRepositoryImpl(
            httpClient = instance(),
            getPodcastsUseCase = instance(),
            setPodcastUseCase = instance(),
            getEpisodesUseCase = instance(),
            setEpisodeUseCase = instance(),
            getAllEpisodesUseCase = instance()
        )
    }
    bind<ShowsRepository>() with singleton {
        ShowsRepositoryImpl(
            httpClient = instance(),
            getShowsUseCase = instance(),
            setShowsUseCase = instance()
        )
    }
    bind<AuthRepository>() with singleton { AuthRepositoryImpl(auth = instance()) }
}
