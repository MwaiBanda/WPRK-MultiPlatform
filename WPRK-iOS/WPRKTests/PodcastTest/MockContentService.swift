//
//  MockContentService.swift
//  WPRKTests
//
//  Created by Mwai Banda on 5/26/22.
//

import Foundation

class MockContentService: ContentService {
    func getShows(completion: @escaping (Result<[Show], Error>) -> ()) {
        do {
           try MockDataSource.sharedInstance.getShows(completion: completion)
        } catch FetchError.badNetwork {
            print("bad Network")
        } catch FetchError.noInternet {
            print("no Internet")

        } catch FetchError.noData {
            print("no data")

        } catch {
            print("Unknown Error")

        }
    }
    
    func getPodcasts(completion: @escaping (Result<[Podcast], Error>) -> ()) {
        
    }
    
    func getEpisodes(showID: String, completion: @escaping (Result<[Episode], Error>) -> ()) {
        
    }
    
    
}
