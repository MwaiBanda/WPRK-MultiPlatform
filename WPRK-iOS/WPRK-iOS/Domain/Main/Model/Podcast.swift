//
//  Podcast.swift
//  WPRK-iOS
//
//  Created by Mwai Banda on 6/25/22.
//

import Foundation

struct Podcast: Identifiable, Hashable {
    var id: String
    var title: String
    var category: String
    var description: String
    var imageURL: String
    var episodesAvailable: Int
}
