//
//  Episodes.swift
//  WPRK
//
//  Created by Mwai Banda on 10/2/21.

import Foundation

// MARK: - Welcome
struct Podcasts : Codable {   
    let data: [Podcast]
    let meta: PodcastsMetaData
}

// MARK: - WelcomeDatum
struct Podcast: Codable, Identifiable, Equatable {
    static func == (lhs: Podcast, rhs: Podcast) -> Bool {
        return lhs.id == rhs.id
    }
    
    let id, type: String
    let attributes: Attributes
    let relationships: Relationships
}


// MARK: - Attributes
struct Attributes: Codable {
    let author, category, copyright, createdAt: String
    let attributesDescription: String
    let explicit: Bool
    let imageURL: String
    let keywords, language: String
    let multipleSeasons: Bool
    let ownerEmail: String
    let playlistLimit: Int
    let attributesPrivate: Bool
    let secondaryCategory, showType, slug, timeZone: String
    let title, updatedAt: String
    let website: String
    let passwordProtectedFeed: Bool
    let breaker, castbox, castro: String?
    let feedURL: String
    let googlePodcasts: String?
    let iHeartRadio: String?
    let overcast: String?
    let pandora: String?
    let pocketCasts: String?
    let radioPublic, soundcloud, stitcher, tuneIn: String?
    let spotify, applePodcasts, deezer, amazonMusic: String?
    let playerFM, podcastAddict: String?
    let emailNotifications: Bool

    enum CodingKeys: String, CodingKey {
        case author, category, copyright
        case createdAt = "created_at"
        case attributesDescription =  "description"
        case explicit
        case imageURL = "image_url"
        case keywords, language
        case multipleSeasons = "multiple_seasons"
        case ownerEmail = "owner_email"
        case playlistLimit = "playlist_limit"
        case attributesPrivate = "private"
        case secondaryCategory = "secondary_category"
        case showType = "show_type"
        case slug
        case timeZone = "time_zone"
        case title
        case updatedAt = "updated_at"
        case website
        case passwordProtectedFeed = "password_protected_feed"
        case breaker, castbox, castro
        case feedURL = "feed_url"
        case googlePodcasts
        case iHeartRadio, overcast, pandora
        case pocketCasts
        case radioPublic, soundcloud, stitcher, tuneIn, spotify
        case applePodcasts = "apple_podcasts"
        case deezer
        case amazonMusic = "amazon_music"
        case playerFM = "player_FM"
        case podcastAddict = "podcast_addict"
        case emailNotifications = "email_notifications"
    }
}

// MARK: - Relationships
struct Relationships: Codable {
    let episodes: PodcastEpisodes
}

// MARK: - Episodes
struct PodcastEpisodes: Codable {
    let data: [EpisodesDatum]
}

// MARK: - EpisodesDatum
struct EpisodesDatum: Codable {
    let id: String
    let type: TypeEnum
}

enum TypeEnum: String, Codable {
    case episode = "episode"
}

// MARK: - Meta
struct PodcastsMetaData: Codable {
    let currentPage, totalPages, totalCount: Int
}

