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

    func testGetFetchPodcasts() throws {
        sut.fetchPodcasts { _ in }
        XCTAssertEqual(sut.featured.count, [Podcast]().count)
    }

    func testPerformanceExample() throws {
        measure {  }
    }

}
