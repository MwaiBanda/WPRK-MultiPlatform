//
//  ShowPageLinks.swift
//  WPRK-iOS
//
//  Created by Mwai Banda on 6/25/22.
//

import Foundation

// MARK: - ShowPageLinks
struct ShowPageLinksSW: Codable {
    let currentPage, nextPage, lastPage: Link

    enum CodingKeys: String, CodingKey {
        case currentPage = "self"
        case nextPage = "next"
        case lastPage = "last"
    }
}
