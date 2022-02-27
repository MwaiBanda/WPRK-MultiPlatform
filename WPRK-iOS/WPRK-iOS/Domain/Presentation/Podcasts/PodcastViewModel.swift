//
//  PodcastViewModel.swift
//  WPRK-iOS
//
//  Created by Mwai Banda on 2/26/22.
//

import Foundation
import SwiftUI

final class PodcastViewModel: ObservableObject {
    var contentAPI: ContentAPI
    var group: DispatchGroup
    @Published var podcasts = [Podcast]()
    @Published var featured = [Episode]()
    @Published var selectedFeatured: Podcast? = nil
    
    init(contentAPI: ContentAPI, group: DispatchGroup){
        self.contentAPI = contentAPI
        self.group = group
        fetchPodcasts { self.selectedFeatured = $0 }
    }
    func fetchPodcasts(onCompletion: @escaping (Podcast) -> Void){
        group.enter()
        contentAPI.getPodcasts { result in
            switch(result) {
            case .success(let podcasts):
                self.podcasts = podcasts
                guard let firstPodcast = podcasts.first else { return }
                self.fetchFeatured(showID: firstPodcast.id)
                onCompletion(firstPodcast)
                
            case .failure(let error):
                print(error.localizedDescription )
            }
        }
        group.leave()
    }
    func fetchFeatured(showID: String) {
        group.enter()
        featured.removeAll()
        contentAPI.getEpisode(showID: showID) { result in
            switch(result) {
            case .success(let episodes):
                episodes.sorted(
                    by: { return $0.attributes.number > $1.attributes.number }
                ).forEach { episode in
                    if self.featured.count < 4 {
                        self.featured.append(episode)
                    }
                }
            case .failure(let error):
                print(error)
            }
        }
        group.leave()
    }
}
