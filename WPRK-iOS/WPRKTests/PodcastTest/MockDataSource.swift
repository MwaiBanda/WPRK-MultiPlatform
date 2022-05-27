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
        let shows = [Shows](repeating:Shows.init(items: [Show](), links: WelcomeLinks(linksSelf: Last(href: ""), next: Last(href: ""), last: Last(href: "")), meta: ShowsMetaData(totalCount: 10, pageCount: 2, currentPage: 1, perPage: 5)), count: 10)
        if shows.isEmpty {
            throw FetchError.noData(description: "no data")
        }
        shows.forEach { _ in
            print("show")
        }
        
    }
    
    func getPodcasts(completion: @escaping (Result<[Podcast], Error>) throws-> ()) {
        
    }
    
    func getEpisodes(showID: String, completion: @escaping (Result<[Episode], Error>) throws-> ()) {
        
    }
    
    private init () { }
    static let sharedInstance = MockDataSource()
}
