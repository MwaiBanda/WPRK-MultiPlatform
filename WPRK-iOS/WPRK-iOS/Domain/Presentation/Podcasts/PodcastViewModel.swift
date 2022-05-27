//
//  PodcastViewModel.swift
//  WPRK-iOS
//
//  Created by Mwai Banda on 2/26/22.
//

import Foundation
import SwiftUI

final class PodcastViewModel: ObservableObject {
    var contentService: ContentService
    var group: DispatchGroup?
    @Published var podcasts = [Podcast]()
    @Published var featured = [Episode]()
    @Published var episodes = [Episode]()
    @Published var selectedFeatured: Podcast? = nil
    
    init(contentService: ContentService, group: DispatchGroup? = nil){
        self.contentService = contentService
        self.group = group
        getPodcasts { self.selectedFeatured = $0 }
    }
    
    private func fetchEpisodes(showID: String, onCompletion: @escaping ([Episode]) -> Void){
        contentService.getEpisodes(showID: showID) { result in
            switch(result){
            case .success(let episodes):
                onCompletion(episodes)
            case .failure(let err):
                print(err.localizedDescription)
            }
        }
    }
    
    private func fetchPodcasts(onCompletion: @escaping ([Podcast], Podcast) -> Void) {
        contentService.getPodcasts { result in
            switch(result) {
            case .success(let podcasts):
                guard let firstPodcast = podcasts.first else { return }
                onCompletion(podcasts, firstPodcast)
            case .failure(let error):
                print(error.localizedDescription )
            }
        }
    }
    
    func getFeatured(showID: String) {
        if let group = group {
            group.enter()
            featured.removeAll()
            fetchEpisodes(showID: showID) { eps in
                DispatchQueue.main.async {
                    eps.sorted(
                        by: { return $0.attributes.number > $1.attributes.number }
                    ).forEach { episode in
                        if self.featured.count < 4 {
                            self.featured.append(episode)
                        }
                    }
                }
            }
            group.leave()
        } else  {
            fetchEpisodes(showID: showID) { eps in
                eps.forEach { episode in
                    if self.featured.count < 4 {
                        self.featured.append(episode)
                    }
                }
            }
            
        }
    }
    
    func getPodcasts(onCompletion: @escaping (Podcast) -> Void){
        if let group = group {
            group.enter()
            fetchPodcasts { podcasts, firstPodcast in
                DispatchQueue.main.async {
                    self.podcasts = podcasts
                    self.getFeatured(showID: firstPodcast.id)
                    onCompletion(firstPodcast)
                }
            }
            group.leave()
        } else {
            fetchPodcasts { podcasts, firstPodcast in
                self.podcasts = podcasts
                self.getFeatured(showID: firstPodcast.id)
                onCompletion(firstPodcast)
            }
        }
    }
    
    func getEpisodes(showID: String) {
        self.episodes.removeAll()
        fetchEpisodes(showID: showID) { eps in
            self.episodes.append(contentsOf: eps)
        }
    }
}

