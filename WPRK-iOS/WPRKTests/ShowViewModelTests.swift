//
//  WPRKTests.swift
//  WPRKTests
//
//  Created by Mwai Banda on 5/26/22.
//

import XCTest

class ShowViewModelTests: XCTestCase {
    var mockContentService: ContentService!
    var sut: ShowViewModel!
    override func setUpWithError() throws {
        mockContentService = MockContentService()
        sut = ShowViewModel(contentService: mockContentService)
        try super.setUpWithError()
    }

    override func tearDownWithError() throws {
        mockContentService = nil
        sut = nil
        try super.tearDownWithError()
    }

    func testGetShows() throws {
        sut.getShows()
        XCTAssertNotEqual(sut.shows.count, [Show]().count)
    }

    func testPerformanceExample() throws {
        measure { }
    }

}
