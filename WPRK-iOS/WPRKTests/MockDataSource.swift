//
//  MockDataSource.swift
//  WPRKTests
//
//  Created by Mwai Banda on 5/26/22.
//

import Foundation

public enum FetchError: Error {
    case badNetwork(description: String)
    case noData(description: String)
    case noInternet(description: String)
}

class MockDataSource {
    func getShows(completion: @escaping (Result<[Show], Error>) -> ()) throws {
        let showResponse = Shows(items: [Show](repeating: Show(id: 10856, start: "2022-05-29T16:00:00+0000", end: "2022-05-29T18:00:00+0000", duration: 7200, timezone: "America/New_York", oneOff: false, title: "Roots Uprising", itemDescription: "Spiritual Healing...Enlightenment...A musical extravaganza to put some \nFunk in your soul, Love in your heart, and Consciousness on your mind!", url: "", hideDj: 0, image: "", links: ItemLinks(linksSelf: Last(href: ""), personas: [Last](), playlists: Last(href: ""))), count: 10), links: WelcomeLinks(linksSelf: Last(href: ""), next: Last(href: ""), last: Last(href: "")), meta: ShowsMetaData(totalCount: 10, pageCount: 2, currentPage: 1, perPage: 5))
        if showResponse.items.isEmpty {
            throw FetchError.noData(description: "no data")
            
        }

        completion(.success(showResponse.items))
    }
    
    func getPodcasts(completion: @escaping (Result<[Podcast], Error>)-> ()) throws {
        let podcastResponse = Podcasts(data: [Podcast](), meta: PodcastsMetaData(currentPage: 1, totalPages: 1, totalCount: 0))
        if podcastResponse.data.isEmpty {
            completion(.failure(FetchError.noData(description: "No data")))
        }
        completion(.success(podcastResponse.data))
    }
    
    func getEpisodes(showID: String, completion: @escaping (Result<[Episode], Error>) -> ()) throws{
        
    }
    
    private init () { }
    static let sharedInstance = MockDataSource()
}
