//
//  Episodes.swift
//  WPRK (iOS)
//
//  Created by Mwai Banda on 10/2/21.
//

import Foundation



// MARK: - EpisodeDTO
struct EpisodeDTO: Codable {
    let id: String
    let type: String
    let attributes: EpisodeAttributes
    let relationships: EpisodeRelationships
}
extension EpisodeDTO {
    func toEpisode() -> Episode {
        return Episode(
            id: id,
            title: attributes.title,
            number: attributes.number,
            description: attributes.attributesDescription,
            mediaURL: attributes.mediaURL,
            durationInMmss: attributes.durationInMmss
        )
    }
}
// MARK: - Attributes
struct EpisodeAttributes: Codable {
    let title: String
    let number, season: Int
    let status: String
    let publishedAt: String
    let duration: Int
    let explicit: Bool
    let keywords, alternateURL: String
    let mediaURL: String
    let imageURL: String?
    let author, summary, attributesDescription, createdAt: String
    let updatedAt, formattedPublishedAt, durationInMmss: String
    let shareURL: String
    let formattedSummary, embedHTML, embedHTMLDark: String
    let audioProcessing: Bool
    let type: String
    let emailNotifications: String?

    enum CodingKeys: String, CodingKey {
        case title, number, season, status
        case publishedAt = "published_at"
        case duration, explicit, keywords
        case alternateURL = "alternate_url"
        case mediaURL = "media_url"
        case imageURL = "image_url"
        case author, summary
        case attributesDescription = "description"
        case createdAt = "created_at"
        case updatedAt = "updated_at"
        case formattedPublishedAt = "formatted_published_at"
        case durationInMmss = "duration_in_mmss"
        case shareURL = "share_url"
        case formattedSummary = "formatted_summary"
        case embedHTML = "embed_html"
        case embedHTMLDark = "embed_html_dark"
        case audioProcessing = "audio_processing"
        case type
        case emailNotifications = "email_notifications"
    }
}

// MARK: - Relationships
struct EpisodeRelationships: Codable {
    let show: EpisodeShow
}

// MARK: - Show
struct EpisodeShow: Codable {
    let data: DataClass
}

// MARK: - DataClass
struct DataClass: Codable {
    let id: String
    let type: String
}

