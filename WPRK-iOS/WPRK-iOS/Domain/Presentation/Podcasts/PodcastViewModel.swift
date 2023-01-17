//
//  PodcastViewModel.swift
//  WPRK-iOS
//
//  Created by Mwai Banda on 2/26/22.
//

import Foundation
import SwiftUI
import WPRKSDK

final class PodcastViewModel: ObservableObject {
    var group: DispatchGroup?
    @Published var podcasts = [Podcast]()
    @Published var featured = [Episode]()
    @Published var episodes = [Episode]()
    @Published var selectedFeatured: Podcast? = nil
    
    init(group: DispatchGroup? = nil){
        self.group = group
    }
    
    private func fetchEpisodes(
        showID: String,
        onCompletion: @escaping ([Episode]) -> Void
    ) async {
        do {
            try await WPRK.shared
                .getEpisodesUseCase
                .invoke(
                    showID: showID,
                    pageNumber: Int32(1)
                ) { res in
                if let data = res.data {
                    self.episodes = data.third as? [Episode] ?? []
                    onCompletion(data.third as? [Episode] ?? [])
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
    
    func getFeatured(showID: String) async {
        if let group = group {
            group.enter()
            featured.removeAll()
            await fetchEpisodes(showID: showID) { eps in
                DispatchQueue.main.async {
                    eps.sorted(
                        by: { return $0.number > $1.number }
                    ).forEach { episode in
                        if self.featured.count < 4 {
                            self.featured.append(episode)
                        }
                    }
                }
            }
            group.leave()
        } else  {
            await fetchEpisodes(showID: showID) { eps in
                eps.forEach { episode in
                    if self.featured.count < 4 {
                        self.featured.append(episode)
                    }
                }
            }
            
        }
    }
    
    func getPodcasts() async {
        if let group = group {
            group.enter()
            await fetchPodcasts()
            await self.getFeatured(showID: podcasts.first?.id ?? "")
            group.leave()
        } else {
            await fetchPodcasts()
            await self.getFeatured(showID: podcasts.first?.id ?? "")

        }
    }
    
    func getEpisodes(showID: String) async {
        self.episodes.removeAll()
        await fetchEpisodes(showID: showID) { eps in
            self.episodes.append(contentsOf: eps)
        }
    }
}

