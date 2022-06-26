//
//  ShowsDTO.swift
//  WPRK-iOS
//
//  Created by Mwai Banda on 6/25/22.
//

import Foundation

// MARK: - ShowsDTO
struct ShowsDTO: Decodable {
    var collection: [ShowDTO]
    var pageLinks: ShowPageLinks
    var meta: ShowsMetaData

       enum CodingKeys: String, CodingKey {
           case collection = "items"
           case pageLinks = "_links"
           case meta = "_meta"
       }
}
