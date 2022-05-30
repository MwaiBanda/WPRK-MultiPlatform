//
//  PodcastViewModelTests.swift
//  WPRKTests
//
//  Created by Mwai Banda on 5/27/22.
//

import XCTest

class PodcastViewModelTests: XCTestCase {
    var mockContentService: ContentService!
    var sut: PodcastViewModel!
    
    override func setUpWithError() throws {
        mockContentService = MockContentService()
        sut = PodcastViewModel(contentService: mockContentService)
        try super.setUpWithError()
    }

    override func tearDownWithError() throws {
        mockContentService = nil
        sut = nil
        try super.tearDownWithError()
    }

    func testGetAllPodcasts() throws {
        sut.getPodcasts { _ in }
        XCTAssertNotEqual(sut.podcasts.count, [Podcast]().count)
    }

    func testGetFeaturedEpisodes() throws {
        sut.featured.removeAll()
        sut.getFeatured(showID: Contants.testID)
        XCTAssertTrue(sut.featured.count == 4)
    }
    
    func testGetAllEpisodes() throws {
        sut.getEpisodes(showID: Contants.testID)
        XCTAssertTrue(sut.episodes.count > 4)
    }
    
    func testEpisodeSortOrder() throws {
        sut.getEpisodes(showID: Contants.testID)
        var episodes = sut.episodes
        episodes = Array(episodes.dropFirst(9)).shuffled()
        episodes = episodes.sorted(by: { return $0.attributes.number > $1.attributes.number })
        XCTAssertLessThan(episodes[1].attributes.number, episodes[0].attributes.number)
    }

    func testPerformance() throws {
        measure {  sut.getFeatured(showID: Contants.testID) }
    }

}
