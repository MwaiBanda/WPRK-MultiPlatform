//
//  MockContentService.swift
//  WPRKTests
//
//  Created by Mwai Banda on 5/26/22.
//

import Foundation
@testable import WPRK

final class MockContentService: ContentService {
    func getShows(onCompletion completion: @escaping (Result<[Show], Error>) -> ()) {
        do {
            try MockDataSource.sharedInstance.getShows { res in
                switch res {
                case .success(let showsDTOs):
                    completion(.success(showsDTOs.map({ $0.toShow() })))
                case .failure(let error):
                    print(error.localizedDescription)
                }
            }
        } catch FetchError.badNetwork(let description) {
            print("Error: \(description)")
        } catch FetchError.noInternet(let description) {
            print("Error: \(description)")
        } catch FetchError.noData(let description) {
            print("Error: \(description)")
        } catch {
            print("Unknown Error")
            
        }
    }
    
    func getPodcasts(onCompletion completion: @escaping (Result<[Podcast], Error>) -> ()) {
        do {
            try MockDataSource.sharedInstance.getPodcasts{ res in
                switch res {
                case .success(let podcastDTOs):
                    completion(.success(podcastDTOs.map({ $0.toPodcast() })))
                case .failure(let error):
                    print(error.localizedDescription)
                }
            }
        } catch FetchError.badNetwork(let description) {
            print("Error: \(description)")
        } catch FetchError.noInternet(let description) {
            print("Error: \(description)")
        } catch FetchError.noData(let description) {
            print("Error: \(description)")
        } catch {
            print("Unknown Error")
            
        }
    }
    
    func getEpisodes(showID: String, onCompletion completion: @escaping (Result<[Episode], Error>) -> ()) {
        do {
            try MockDataSource.sharedInstance.getEpisodes(showID: showID) { res in
                switch res {
                case .success(let episodeDTOs):
                    completion(.success(episodeDTOs.map({ $0.toEpisode() })))
                case .failure(let error):
                    print(error.localizedDescription)
                }
            }
        } catch FetchError.badNetwork(let description) {
            print("Error: \(description)")
        } catch FetchError.noInternet(let description) {
            print("Error: \(description)")
        } catch FetchError.noData(let description) {
            print("Error: \(description)")
        } catch {
            print("Unknown Error")
            
        }
    }
    
    
}
