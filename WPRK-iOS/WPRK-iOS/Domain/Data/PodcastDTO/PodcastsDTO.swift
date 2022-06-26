//
//  PodcastsDTO.swift
//  WPRK-iOS
//
//  Created by Mwai Banda on 6/25/22.
//

import Foundation

// MARK: - PodcastsMetaData
struct PodcastsDTO: Codable {
    let collection: [PodcastDTO]
    let metaData: PodcastsMetaData
    enum CodingKeys: String, CodingKey {
        case collection = "data"
        case metaData = "meta"
    }
}
