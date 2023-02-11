//
//  PodcastViewModel.swift
//  WPRK-iOS
//
//  Created by Mwai Banda on 2/26/22.
//

import Foundation
import SwiftUI
import WPRKSDK

@MainActor
final class PodcastViewModel: ObservableObject {
    @Published var podcasts = [Podcast]()
    @Published var featured = [Episode]()
    @Published var episodes = [Episode]()
    @Published var selectedFeatured: Podcast? = nil
    @Published var canLoadMore = false
    @Published var currentPage = 1
    
    private func fetchEpisodes(
        isFeatured: Bool = false,
        showID: String,
        onCompletion: @escaping ([Episode]) -> Void = {_ in }
    ) async {
        do {
            try await WPRK.shared
                .getEpisodesUseCase
                .invoke(
                    showID: showID,
                    pageNumber: Int32(isFeatured ? 1 : currentPage)
                ) { res in
                    if let data = res.data {
                        self.canLoadMore = data.first?.boolValue ?? false
                        self.currentPage = (data.second?.intValue ?? 1) + 1
                        if isFeatured {
                            (data.third as? [Episode] )?
                                .sorted(by: { return $0.number > $1.number })
                                .forEach {
                                    if self.featured.count < 4 {
                                        self.featured.append($0)
                                    }
                                }
                            print("Fin")

                        } else {
                            self.episodes +=  Array(Set(data.third as? [Episode] ?? [] + self.episodes)).sorted(by: { return $0.number > $1.number })
                        }
                    } else if let error = res.message {
                        print(error)
                    }
                }
        } catch {
            print(error.localizedDescription)
        }
    }
    
    private func fetchPodcasts() async {
        do {
            try await WPRK.shared.getPodcastsUseCase.invoke { res in
                if let data = res.data {
                    guard let firstPodcast  = (data as? [Podcast])?.first else { return }
                    self.podcasts = data as? [Podcast] ?? []
                    self.selectedFeatured = firstPodcast
                    
                }
            }
        } catch {
            print(error.localizedDescription)
        }
    }
    
    private func getFeatured(showID: String) async {
        await fetchEpisodes(isFeatured: true, showID: showID)
    }
    
    func getFeatured(showID: String) {
        Task.detached {
            await self.getFeatured(showID: showID)
        }
    }
    
    private func getPodcasts() async {
        let group = DispatchGroup()
        group.enter()
        await self.fetchPodcasts()
        group.leave()
        group.enter()
        await self.getFeatured(showID: podcasts.first?.id ?? "")
        group.leave()

    }
    
    func getPodcasts()  {
        Task.detached {
            await self.getPodcasts()
        }
    }
    
    private func getEpisodes(showID: String) async {
        await fetchEpisodes(showID: showID)
    }
    
    func getEpisodes(showID: String) {
        Task.detached {
            await self.getEpisodes(showID: showID)
        }
    }
}

