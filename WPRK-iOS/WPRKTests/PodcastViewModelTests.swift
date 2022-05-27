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
        sut.getFeatured(showID: "Test")
        XCTAssertTrue(sut.featured.count == 4)
    }

    func testPerformance() throws {
        measure {  }
    }

}
