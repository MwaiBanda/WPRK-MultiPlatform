//
//  ContentView.swift
//  Shared
//
//  Created by Mwai Banda on 9/9/21.
//

import SwiftUI

struct ContentView: View {
    @ObservedObject var networkService: NetworkService
    @ObservedObject var podcastViewModel: PodcastViewModel

    var body: some View {
        ConnectivityWrapper(networkService: networkService) {
            TabBar(podcastViewModel: podcastViewModel)
        }
    }
}


