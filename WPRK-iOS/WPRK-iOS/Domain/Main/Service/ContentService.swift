//
//  ContentAPI.swift
//  WPRK-iOS
//
//  Created by Mwai Banda on 5/26/22.
//

import Foundation

protocol ContentService {
    func getShows(completion: @escaping (Result<[Show], Error>) -> ())
    func getPodcasts(completion: @escaping (Result<[Podcast], Error>) -> ())
    func getEpisodes(showID: String, completion: @escaping (Result<[Episode], Error>) -> ())
}
