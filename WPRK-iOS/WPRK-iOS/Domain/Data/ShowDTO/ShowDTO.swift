//
//  Shows.swift
//  WPRK
//
//  Created by Mwai Banda on 10/2/21.
//

import Foundation


// MARK: - ShowDTO
struct ShowDTO: Decodable {
    var id: Int
    var start, end: String
    var duration: Int
    var timezone: String
    var oneOff: Bool
    var category: Category?
    var title, itemDescription: String
    var since: Int?
    var url: String
    var hideDj: Int
    var image: String
    var links: ShowLinks

    enum CodingKeys: String, CodingKey {
        case id, start, end, duration, timezone
        case oneOff = "one_off"
        case category, title
        case itemDescription = "description"
        case since, url
        case hideDj = "hide_dj"
        case image
        case links = "_links"
    }
}

extension ShowDTO {
    func toShow() -> Show {
        return Show(
            id: id,
            category: category == .unset ? "WPRK" : category?.rawValue,
            description: itemDescription,
            duration: duration,
            end: end,
            image: image,
            since: since,
            start: start,
            timezone: timezone,
            title: title,
            url: url
        )
    }
}

// MARK: - Category
enum Category: String, Codable {
    case music = "Music"
    case talk = "Talk"
    case sport = "Sport"
    case unset = "unset"
    case news = "News"
}

// MARK: - ShowLinks
struct ShowLinks: Codable {
    var currentShow: Link
    var personas: [Link]
    var playlists: Link

    enum CodingKeys: String, CodingKey {
        case currentShow = "self"
        case personas, playlists
    }
}

// MARK: - Link
struct Link: Codable {
    var href: String
}



