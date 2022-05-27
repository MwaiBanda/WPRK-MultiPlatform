//
//  MockDataSource.swift
//  WPRKTests
//
//  Created by Mwai Banda on 5/26/22.
//

import Foundation


final class MockDataSource {
    func getShows(completion: @escaping (Result<[Show], Error>) -> ()) throws {
        let showResponse = Shows(items: [Show](repeating: Show(id: 10856, start: "2022-05-29T16:00:00+0000", end: "2022-05-29T18:00:00+0000", duration: 7200, timezone: "America/New_York", oneOff: false, title: "Roots Uprising", itemDescription: "Spiritual Healing...Enlightenment...A musical extravaganza to put some \nFunk in your soul, Love in your heart, and Consciousness on your mind!", url: "", hideDj: 0, image: "", links: ItemLinks(linksSelf: Last(href: ""), personas: [Last](), playlists: Last(href: ""))), count: 10), links: WelcomeLinks(linksSelf: Last(href: ""), next: Last(href: ""), last: Last(href: "")), meta: ShowsMetaData(totalCount: 10, pageCount: 2, currentPage: 1, perPage: 5))
        if showResponse.items.isEmpty {
            throw FetchError.noData(description: "no data")
            
        }

        completion(.success(showResponse.items))
    }
    
    func getPodcasts(completion: @escaping (Result<[Podcast], Error>)-> ()) throws {
        let podcastResponse = Podcasts(data: [Podcast](repeating: Podcast(id: "1001", type: "", attributes: Attributes(author: "", category: "", copyright: "", createdAt: "", attributesDescription: "", explicit: false, imageURL: "", keywords: "", language: "", multipleSeasons: false, ownerEmail: "", playlistLimit: 0, attributesPrivate: false, secondaryCategory: "", showType: "", slug: "", timeZone: "", title: "", updatedAt: "", website: "", passwordProtectedFeed: true, breaker: "", castbox: "", castro: "", feedURL: "", googlePodcasts: "", iHeartRadio: "", overcast: "", pandora: "", pocketCasts: "", radioPublic: "", soundcloud: "", stitcher: "", tuneIn: "", spotify: "", applePodcasts: "", deezer: "", amazonMusic: "", playerFM: "", podcastAddict: "", emailNotifications: false), relationships: Relationships(episodes: PodcastEpisodes(data: [EpisodesDatum]()))), count: 10), meta: PodcastsMetaData(currentPage: 1, totalPages: 1, totalCount: 0))
        if podcastResponse.data.isEmpty {
            throw FetchError.noData(description: "No data")
        }
        completion(.success(podcastResponse.data))
    }
    
    func getEpisodes(showID: String, completion: @escaping (Result<[Episode], Error>) -> ()) throws{
        if showID.isEmpty {
            throw FetchError.invalidID(description: "")
        }
        var episodesResponse = [Episode](repeating: Episode(id: "1001", type: "", attributes: EpisodeAttributes(title: "", number: 0, season: 0, status: "", publishedAt: "", duration: 0, explicit: false, keywords: "", alternateURL: "", mediaURL: "", imageURL: "", author: "", summary: "", attributesDescription: "", createdAt: "", updatedAt: "", formattedPublishedAt: "", durationInMmss: "", shareURL: "", formattedSummary: "", embedHTML: "", embedHTMLDark: "", audioProcessing: false, type: "", emailNotifications: ""), relationships: EpisodeRelationships(show: EpisodeShow(data: DataClass(id: "", type: "")))), count: 10)
        episodesResponse.append(Episode(id: "1001", type: "", attributes: EpisodeAttributes(title: "", number: 1, season: 0, status: "", publishedAt: "", duration: 0, explicit: false, keywords: "", alternateURL: "", mediaURL: "", imageURL: "", author: "", summary: "", attributesDescription: "", createdAt: "", updatedAt: "", formattedPublishedAt: "", durationInMmss: "", shareURL: "", formattedSummary: "", embedHTML: "", embedHTMLDark: "", audioProcessing: false, type: "", emailNotifications: ""), relationships: EpisodeRelationships(show: EpisodeShow(data: DataClass(id: "", type: "")))))
        episodesResponse.append(Episode(id: "1001", type: "", attributes: EpisodeAttributes(title: "", number: 2, season: 0, status: "", publishedAt: "", duration: 0, explicit: false, keywords: "", alternateURL: "", mediaURL: "", imageURL: "", author: "", summary: "", attributesDescription: "", createdAt: "", updatedAt: "", formattedPublishedAt: "", durationInMmss: "", shareURL: "", formattedSummary: "", embedHTML: "", embedHTMLDark: "", audioProcessing: false, type: "", emailNotifications: ""), relationships: EpisodeRelationships(show: EpisodeShow(data: DataClass(id: "", type: "")))))
        episodesResponse.append(Episode(id: "1001", type: "", attributes: EpisodeAttributes(title: "", number: 3, season: 0, status: "", publishedAt: "", duration: 0, explicit: false, keywords: "", alternateURL: "", mediaURL: "", imageURL: "", author: "", summary: "", attributesDescription: "", createdAt: "", updatedAt: "", formattedPublishedAt: "", durationInMmss: "", shareURL: "", formattedSummary: "", embedHTML: "", embedHTMLDark: "", audioProcessing: false, type: "", emailNotifications: ""), relationships: EpisodeRelationships(show: EpisodeShow(data: DataClass(id: "", type: "")))))
        if episodesResponse.isEmpty {
            throw FetchError.noData(description: "no data")
            
        }
        completion(.success(episodesResponse))
        
    }
    
    private init () { }
    static let sharedInstance = MockDataSource()
}
