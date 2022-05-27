//
//  MockContentService.swift
//  WPRKTests
//
//  Created by Mwai Banda on 5/26/22.
//

import Foundation

final class MockContentService: ContentService {
    func getShows(completion: @escaping (Result<[Show], Error>) -> ()) {
        do {
            try MockDataSource.sharedInstance.getShows(completion: completion)
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
    
    func getPodcasts(completion: @escaping (Result<[Podcast], Error>) -> ()) {
        do {
            try MockDataSource.sharedInstance.getPodcasts(completion: completion)
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
    
    func getEpisodes(showID: String, completion: @escaping (Result<[Episode], Error>) -> ()) {
        do {
            try MockDataSource.sharedInstance.getEpisodes(showID: showID, completion: completion)
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
