//
//  Podcasts.swift
//  WPRK (iOS)
//
//  Created by Mwai Banda on 9/10/21.
//

import SwiftUI
import SDWebImageSwiftUI
import AVFoundation

struct PodcastHome: View {
    @ObservedObject var streamer: WPRKStreamer
    @State private var selected: Podcast? = nil
    @ObservedObject var podcastViewModel: PodcastViewModel
    var body: some View {
        ScrollView(showsIndicators: false) {
            VStack {
                
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
                                WebImage(url: URL(string: podcast.attributes.imageURL))
                                    .resizable()
                                    .aspectRatio(contentMode: .fill)
                                    .frame(width: 190, height: 200, alignment: .center)
                                    .cornerRadius(10)
                                    .foregroundColor(.gray)
                                    .onTapGesture {
                                        selected = podcast
                                        let haptic = UIImpactFeedbackGenerator(style: .soft)
                                        haptic.impactOccurred()
                                        
                                    }
                            }
                        }
                    }
                }
                VStack(alignment: .leading) {
                    HStack {
                        Text("Featured Episodes")
                            .font(.title)
                            .fontWeight(.heavy)
                        Spacer()
                        
                        
                    }
                    Text("Discover Popular Episodes From Our Podcasts")
                        .foregroundColor(.gray)
                }
                Divider().background(Color(.gray))
                PodcastTabRow
                Divider().background(Color.gray)
                ScrollView(.vertical, showsIndicators: false) {
                    if podcastViewModel.featured.isEmpty {
                        ForEach(0..<5, id: \.self) { i in
                            ContentRow(episode: Episode(id: "", type: "", attributes: EpisodeAttributes(title: "Lorem ipsum dolor sit amet", number: 0, season: 0, status: "", publishedAt: "", duration: 0, explicit: false, keywords: "", alternateURL: "", mediaURL: "", imageURL: "", author: "", summary: "", attributesDescription: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean laoreet ornare dapibus. Cras eu metus scelerisque, ullamcorper ex vestibulum, pretium purus. Ut quis elementum sapien. Phasellus eget magna in nunc pharetra interdum eu id elit. Maecenas sapien lectus, congue ut semper et, malesuada vitae ligula. Lorem ipsum dolor sit amet, consectetur adipiscing elit.", createdAt: "", updatedAt: "", formattedPublishedAt: "", durationInMmss: "", shareURL: "", formattedSummary: "", embedHTML: "", embedHTMLDark: "", audioProcessing: false, type: "", emailNotifications: ""), relationships: EpisodeRelationships(show: EpisodeShow(data: DataClass(id: "", type: "")))), streamer: streamer, paddingVertical: 10)
                        }
                    } else {
                        ForEach(podcastViewModel.featured, id: \.id) { i in
                            ContentRow(showTitle: podcastViewModel.selectedFeatured?.attributes.title ,episode: i, streamer: streamer, paddingVertical: 10)
                            if podcastViewModel.featured.last?.id != i.id {
                                Divider().background(Color(.lightGray))
                            } else {
                                Divider().background(Color(.white))
                                HStack {
                                    Text("\(podcastViewModel.selectedFeatured?.relationships.episodes.data.count ?? 0) Episodes Available")
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
                .frame(height: DeviceType.deviceIsPad ? .infinity : (DeviceType.iPhone678   ? screenBounds.height - screenBounds.height * 0.545  :DeviceType.iPhone12 || DeviceType.iPhoneX  ? screenBounds.height - screenBounds.height * 0.51 : DeviceType.iPhone678p ? screenBounds.height - screenBounds.height * 0.495 : screenBounds.height - screenBounds.height * 0.465))
                Spacer()
            }
            .padding(.top, 5)
            .foregroundColor(.white)
            .background(Color.white.opacity(0).ignoresSafeArea(.all))
            .onAppear {
                AppReviewRequest.RequestReviewWhenNeeeded()
            }
            .redacted(reason: podcastViewModel.podcasts.isEmpty ? .placeholder : [])
        }
        
        .sheet(item: $selected) { podcast in
            ContentWrapper(streamer: streamer, navConfig: .detailConfig, navTitle: podcast.attributes.title) {
                PodcastDetail(podcast: podcast, streamer: streamer, podcastViewModel: podcastViewModel)
            }
        }
    }
    var PodcastTabRow: some View {
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
                        ForEach(podcastViewModel.podcasts, id: \.id) { i in
                            HStack {
                                VStack {
                                    Text(i.attributes.title)
                                        .fontWeight(.heavy)
                                    
                                }
                                .padding()
                                .padding(.horizontal)
                                .background(i.id == podcastViewModel.selectedFeatured?.id  ? Color.clear : Color(hex: 0xffafcc))
                                .cornerRadius(10)
                                .overlay(i.id == podcastViewModel.selectedFeatured?.id ? RoundedRectangle(cornerRadius: 10).stroke(Color.clear, lineWidth: 1.5) : RoundedRectangle(cornerRadius: 10).stroke(Color.white, lineWidth: 1.5))
                                .padding(2)
                                .padding(.trailing)
                                .onTapGesture {
                                    podcastViewModel.selectedFeatured = i
                                    podcastViewModel.getFeatured(showID: podcastViewModel.selectedFeatured?.id ?? "")
                                    let haptic = UIImpactFeedbackGenerator(style: .soft)
                                    haptic.impactOccurred()
                                    if i != podcastViewModel.podcasts.last {
                                        value.scrollTo(i.attributes.title , anchor: .center)
                                    } else {
                                        value.scrollTo(i.attributes.title, anchor: .trailing)
                                    }
                                }
                                
                            }.id(i.attributes.title)
                        }
                    }
                }
            }
        }
    }
}


