//
//  EpisodesDTO.swift
//  WPRK-iOS
//
//  Created by Mwai Banda on 6/25/22.
//

import Foundation

// MARK: - Episodes
struct EpisodesDTO: Codable {
    let collection: [EpisodeDTO]
    let meta: EpisodeMeta
    enum CodingKeys: String, CodingKey {
        case collection = "data"
        case meta
    }
}
