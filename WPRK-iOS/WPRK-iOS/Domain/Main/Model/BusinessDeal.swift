//
//  BusinessDeals.swift
//  WPRK (iOS)
//
//  Created by Mwai Banda on 10/8/21.
//

import Foundation


struct BusinessDeal: Identifiable, Hashable {
    var id = UUID()
    let category: String
    let title: String
    let description: String
    let contact: String
    let address: String
}
