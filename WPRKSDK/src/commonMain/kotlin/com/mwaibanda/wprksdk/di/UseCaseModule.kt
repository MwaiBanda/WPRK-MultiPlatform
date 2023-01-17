package com.mwaibanda.wprksdk.di

import com.mwaibanda.wprksdk.main.model.Podcast
import com.mwaibanda.wprksdk.main.usecase.auth.LoginWithEmailAndPasswordUseCase
import com.mwaibanda.wprksdk.main.usecase.auth.SignInInAsAGuestUseCase
import com.mwaibanda.wprksdk.main.usecase.auth.SignInWithEmailAndPasswordUseCase
import com.mwaibanda.wprksdk.main.usecase.cache.GetAllItemsUseCase
import com.mwaibanda.wprksdk.main.usecase.cache.GetItemUseCase
import com.mwaibanda.wprksdk.main.usecase.cache.SetItemUseCase
import com.mwaibanda.wprksdk.main.usecase.podcasts.GetEpisodesUseCase
import com.mwaibanda.wprksdk.main.usecase.podcasts.GetPodcastsUseCase
import com.mwaibanda.wprksdk.main.usecase.shows.GetShowUseCase
import com.mwaibanda.wprksdk.util.EpisodeResponse
import com.mwaibanda.wprksdk.util.PodcastResponse
import com.mwaibanda.wprksdk.util.ShowResponse
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

val UseCaseModule = DI.Module("SDK/UseCase") {
    bind<GetEpisodesUseCase>() with singleton {
        GetEpisodesUseCase(podcastRepository = instance())
    }
    bind<GetPodcastsUseCase>() with singleton {
        GetPodcastsUseCase(podcastRepository = instance())
    }

    bind<GetShowUseCase>() with singleton {
        GetShowUseCase(showsRepository = instance())
    }

    bind<LoginWithEmailAndPasswordUseCase>() with singleton { LoginWithEmailAndPasswordUseCase(authRepository = instance()) }
    bind<SignInInAsAGuestUseCase>() with singleton { SignInInAsAGuestUseCase(authRepository = instance()) }
    bind<SignInWithEmailAndPasswordUseCase>() with singleton { SignInWithEmailAndPasswordUseCase(authRepository = instance()) }
    /**
     * @cache
     */
    bind<GetItemUseCase<PodcastResponse>>() with singleton { GetItemUseCase(cacheControl = instance()) }
    bind<GetItemUseCase<ShowResponse>>() with singleton { GetItemUseCase(cacheControl = instance()) }
    bind<GetItemUseCase<EpisodeResponse>>() with singleton { GetItemUseCase(cacheControl = instance()) }
    bind<SetItemUseCase<PodcastResponse>>() with singleton { SetItemUseCase(cacheControl = instance()) }
    bind<SetItemUseCase<ShowResponse>>() with singleton { SetItemUseCase(cacheControl = instance()) }
    bind<SetItemUseCase<EpisodeResponse>>() with singleton { SetItemUseCase(cacheControl = instance()) }
    bind<GetAllItemsUseCase<EpisodeResponse>>() with singleton { GetAllItemsUseCase(cacheControl = instance()) }
}