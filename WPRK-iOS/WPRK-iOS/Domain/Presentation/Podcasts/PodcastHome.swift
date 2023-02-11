//
//  Podcasts.swift
//  WPRK (iOS)
//
//  Created by Mwai Banda on 9/10/21.
//

import SwiftUI
import SDWebImageSwiftUI
import AVFoundation
import WPRKSDK
import Introspect

struct PodcastHome: View {
    @ObservedObject var streamer: WPRKStreamer
    @State private var selected: Podcast? = nil
    @ObservedObject var podcastViewModel: PodcastViewModel
    var body: some View {
        ScrollView(showsIndicators: false) {
            LazyVStack(pinnedViews: [.sectionHeaders]) {
                Section {
                    VStack(alignment: .leading) {
                        HStack {
                            Text("Podcasts")
                                .font(.title)
                                .fontWeight(.heavy)
                                .offset(y: -10)
                            Spacer()
                            LiveButton(streamer: streamer)
                        }
                        Text("Tap Featured Podcasts")
                            .offset(y: -30)
                            .foregroundColor(.gray)
                    }.offset(y: 10)
                    Divider().background(Color(.lightGray))
                        .offset(y: -15)
                    ScrollView(.horizontal, showsIndicators: false) {
                        
                        HStack {
                            if podcastViewModel.podcasts.isEmpty {
                                ForEach(0..<10, id: \.self) { i in
                                    RoundedRectangle(cornerRadius: 10)
                                        .frame(width: 190, height: 200, alignment: .center)
                                        .foregroundColor(.gray).opacity(0.5)
                                        .redacted(reason: .placeholder)
                                }
                            } else {
                                ForEach(podcastViewModel.podcasts, id: \.id) { podcast in
                                    WebImage(url: URL(string: podcast.thumbnailURL))
                                        .resizable()
                                        .aspectRatio(contentMode: .fill)
                                        .frame(width: 190, height: 200, alignment: .center)
                                        .cornerRadius(10)
                                        .foregroundColor(.gray)
                                        .onTapGesture {
                                            selected = podcast
                                            podcastViewModel.currentPage = 1
                                            podcastViewModel.episodes.removeAll()
                                            let haptic = UIImpactFeedbackGenerator(style: .soft)
                                            haptic.impactOccurred()
                                            
                                        }
                                }
                            }
                        }
                    }
                }
                Section {
                    ScrollView(.vertical, showsIndicators: false) {
                        if podcastViewModel.featured.isEmpty {
                            ForEach(0..<5, id: \.self) { i in
                                ContentRow(episode: Episode(id: "", title: "Lorem ipsum dolor sit amet", description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean laoreet ornare dapibus. Cras eu metus scelerisque, ullamcorper ex vestibulum, pretium purus. Ut quis elementum sapien. Phasellus eget magna in nunc pharetra interdum eu id elit. Maecenas sapien lectus, congue ut semper et, malesuada vitae ligula. Lorem ipsum dolor sit amet, consectetur adipiscing elit.", number: 0, duration: "",  episodeURL: "", showId: ""))
                                    .redacted(reason: podcastViewModel.featured.isEmpty ? .placeholder : [])

                            }
                        } else {
                            ForEach(podcastViewModel.featured, id: \.id) { i in
                                ContentRow(showTitle: podcastViewModel.selectedFeatured?.title, episode: i, streamer: streamer, paddingVertical: 10)
                                if podcastViewModel.featured.last?.id != i.id {
                                    Divider().background(Color(.lightGray))
                                } else {
                                    Divider().background(Color(.white))
                                    HStack {
                                        Text("\(podcastViewModel.selectedFeatured?.episodesAvailable ?? 0) Episodes Available")
                                            .bold()
                                            .font(.headline)
                                        Spacer()
                                        HStack {
                                            Spacer()
                                            
                                            Image(systemName: "note.text.badge.plus")
                                            Text("See More")
                                        }
                                        .padding(.trailing)
                                        .font(.headline)
                                        
                                    }
                                    .padding(.vertical)
                                    .onTapGesture {
                                        selected = podcastViewModel.selectedFeatured
                                        let haptic = UIImpactFeedbackGenerator(style: .soft)
                                        haptic.impactOccurred()
                                    }
                                    Divider().background(Color(.white))
                                    
                                }
                            }
                        }
                    }
                    .frame(minHeight: 515)
                    
                } header: {
                    
                    VStack(alignment: .leading) {
                        HStack {
                            Text("Featured Episodes")
                                .font(.title)
                                .fontWeight(.heavy)
                            Spacer()
                            
                            
                        }
                        Text("Discover Popular Episodes From Our Podcasts")
                            .foregroundColor(.gray)
                        Divider().background(Color(.gray))
                        PodcastTabRow(podcastViewModel: podcastViewModel)
                        Divider().background(Color.gray)
                    }
                    .padding(.top, 5)
                    .background(Color(.black))

                }

            
               
                Spacer()
            }
            .padding(.top, 5)
            .foregroundColor(.white)
            .background(Color.white.opacity(0).ignoresSafeArea(.all))
            .redacted(reason: podcastViewModel.podcasts.isEmpty ? .placeholder : [])
            .onAppear {
                AppReviewRequest.RequestReviewWhenNeeeded()
                DispatchQueue.main.async() {
                    podcastViewModel.getPodcasts()
                }
            }
        }
        .sheet(item: $selected) { podcast in
            ContentWrapper(streamer: streamer, navConfig: .detailConfig, navTitle: podcast.title) {
                PodcastDetail(podcast: podcast, streamer: streamer, podcastViewModel: podcastViewModel)
            }
        }
    }
}

struct PodcastTabRow: View {
    @ObservedObject var podcastViewModel: PodcastViewModel
    var body: some View {
        
        ScrollViewReader { value in
            ScrollView(.horizontal, showsIndicators: false) {
                HStack {
                    if podcastViewModel.podcasts.isEmpty {
                        ForEach(0..<5, id: \.self) { i in
                            VStack {
                                Text("Lorem ipsum")
                                    .fontWeight(.heavy)
                            }
                            .padding()
                            .padding(.horizontal)
                            .background(Color(hex: 0xffafcc))
                            .cornerRadius(10)
                            .overlay(RoundedRectangle(cornerRadius: 10).stroke(Color.white, lineWidth: 1.5))
                            .padding(2)
                            .padding(.trailing)
                            .redacted(reason: .placeholder)
                        }
                    } else {
                        ForEach(podcastViewModel.podcasts.filter({ $0.title != Constants.ANNIVERSARY }), id: \.id) { i in
                            HStack {
                                VStack {
                                    Text(i.title)
                                        .fontWeight(.heavy)
                                    
                                }
                                .padding()
                                .padding(.horizontal)
                                .background(i.title == podcastViewModel.selectedFeatured?.title  ? Color.clear : Color(hex: 0xffafcc))
                                .cornerRadius(10)
                                .overlay(i.title == podcastViewModel.selectedFeatured?.title ? RoundedRectangle(cornerRadius: 10).stroke(Color.clear, lineWidth: 1.5) : RoundedRectangle(cornerRadius: 10).stroke(Color.white, lineWidth: 1.5))
                                .padding(2)
                                .padding(.trailing)
                                .onTapGesture {
                                    DispatchQueue.main.async() {
                                        let group = DispatchGroup()
                                        podcastViewModel.featured.removeAll()
                                        podcastViewModel.currentPage = 1
                                        podcastViewModel.selectedFeatured = i
                                        
                                        group.enter()
                                        withAnimation(.easeIn(duration: 0.28)) {
                                            if i != podcastViewModel.podcasts.last {
                                                value.scrollTo(i.title , anchor: .center)
                                            } else {
                                                value.scrollTo(i.title, anchor: .trailing)
                                            }
                                        }
                                        podcastViewModel.getFeatured(showID: i.id)
                                        let haptic = UIImpactFeedbackGenerator(style: .soft)
                                        haptic.impactOccurred()
                                        group.leave()
                                    }
                                }
                                
                            }.id(i.title)
                        }
                    }
                }
                
            }
        }
    }
}


extension Podcast: Identifiable {
    
}
