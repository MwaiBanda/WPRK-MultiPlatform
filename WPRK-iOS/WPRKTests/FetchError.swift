//
//  FetchError.swift
//  WPRKTests
//
//  Created by Mwai Banda on 5/27/22.
//

import Foundation

@frozen enum FetchError: Error {
    case badNetwork(description: String)
    case noData(description: String)
    case noInternet(description: String)
    case invalidID(description: String)
}
