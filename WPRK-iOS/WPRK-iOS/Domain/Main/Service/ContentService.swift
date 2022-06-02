//
//  ContentAPI.swift
//  WPRK-iOS
//
//  Created by Mwai Banda on 5/26/22.
//

import Foundation

protocol ContentService {
    func getShows(onCompletion: @escaping (Result<[Show], Error>) -> ())
    func getPodcasts(onCompletion: @escaping (Result<[Podcast], Error>) -> ())
    func getEpisodes(showID: String, onCompletion: @escaping (Result<[Episode], Error>) -> ())
}
