//
//  TabBar.swift
//  WPRK (iOS)
//
//  Created by Mwai Banda on 9/9/21.
//

import SwiftUI
import UIKit
import AVFoundation

struct TabBar: View {
    @StateObject private var streamer = WPRKStreamer.sharedInstance
    @State private var selection = 2
    @ObservedObject var podcastViewModel: PodcastViewModel

    var body: some View {
        TabView(selection: $selection) {
            NavigationView {
                ContentWrapper(streamer: streamer) {
                    VStack(spacing: 0) {
                        ShowHome(streamer: streamer)
                    }
                }
                .onAppear {
                    let haptic = UIImpactFeedbackGenerator(style: .light)
                    haptic.impactOccurred()
                }
            }
            .navigationViewStyle(StackNavigationViewStyle())
            .tabItem {
                Image(systemName: "calendar.badge.clock")
                    .foregroundColor(selection == 1 ? .white : Color(.lightGray))

                Text("Shows")
                    .foregroundColor(selection == 1 ? .white : Color(.lightGray))

            }
            .tag(1)

            NavigationView {
                ContentWrapper(streamer: streamer) {
                    VStack {
                        PodcastHome(streamer: streamer, podcastViewModel: podcastViewModel)
                      
                    }
                    
                }
                .onAppear {
                    let haptic = UIImpactFeedbackGenerator(style: .light)
                    haptic.impactOccurred()
                }
            }
            .navigationViewStyle(StackNavigationViewStyle())
            .tabItem {
  
                    
                Image(systemName: "waveform.badge.plus")
                    .foregroundColor(selection == 2 ? .white : Color(.lightGray))

                Text("Podcasts")
                    .foregroundColor(selection == 2 ? .white : Color(.lightGray))


            }

            .tag(2)
            .navigationViewStyle(StackNavigationViewStyle())
            
            NavigationView {
                ContentWrapper(streamer: streamer) {
                    VStack {
                        MembershipHome(streamer: streamer)
                    }
                    
                }
                .onAppear {
                    let haptic = UIImpactFeedbackGenerator(style: .light)
                    haptic.impactOccurred()
                }
            }
            .navigationViewStyle(StackNavigationViewStyle())
            .tabItem {
                Image(systemName: "at.badge.plus")
                    .foregroundColor(selection == 3 ? .white : Color(.lightGray))
                Text("Membership")
                    .foregroundColor(selection == 3 ? .white : Color(.lightGray))

            }

            .tag(3)
        }
        .accentColor(.white)
        
    }
}




