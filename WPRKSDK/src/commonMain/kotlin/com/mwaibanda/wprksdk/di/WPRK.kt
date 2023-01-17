package com.mwaibanda.wprksdk.di

import com.mwaibanda.wprksdk.main.usecase.podcasts.GetEpisodesUseCase
import com.mwaibanda.wprksdk.main.usecase.podcasts.GetPodcastsUseCase
import com.mwaibanda.wprksdk.main.usecase.shows.GetShowUseCase
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

object WPRK: DIAware {
    override val di: DI by DI.lazy {
        import(SingletonModule)
        import(RepositoryModule)
        import(UseCaseModule)
    }

    val getPodcastsUseCase: GetPodcastsUseCase by instance()
    val getEpisodesUseCase: GetEpisodesUseCase by instance()
    val getShowUseCase: GetShowUseCase by instance()
}