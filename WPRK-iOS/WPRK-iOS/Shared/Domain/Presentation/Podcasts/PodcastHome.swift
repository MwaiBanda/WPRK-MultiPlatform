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
    @ObservedObject var streamer: RadioStreamer
    @State private var selected: Podcast? = nil
    @State private var selectedFeatured: Podcast? = nil
    @StateObject var podcastsAPI = ContentAPI()
    @State private var podcasts = [Podcast]()
    @State private var featured = [Episode]()
    let group = DispatchGroup()
    var body: some View {
        ScrollView(showsIndicators: false) {
            VStack {
                Divider().background(Color(.lightGray))
                    .offset(y: 10)
        VStack(alignment: .leading) {
            HStack {
                Text("Podcasts")
                    .font(.largeTitle)
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
                    ForEach(podcasts, id: \.id) { podcast in
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
                VStack(alignment: .leading) {
            HStack {
                Text("Featured Episodes")
                    .font(.largeTitle)
                    .fontWeight(.heavy)
                Spacer()
            
                
            }
            Text("Discover Popular Episodes From Our Podcasts")
                .foregroundColor(.gray)
                }
            Divider().background(Color(.gray))
            ScrollView(.horizontal, showsIndicators: false) {
                HStack {
                    ForEach(podcasts) { i in
                        HStack {
                            VStack {
                                Text(i.attributes.title)
                                    .fontWeight(.heavy)
                               
                            }
                            .padding()
                            .padding(.horizontal)
                          
                            .background(i.id == selectedFeatured?.id ? Color.clear : Color(hex: 0xffafcc))
                            .cornerRadius(10)
                            .overlay(i.id == selectedFeatured?.id ?
                                        RoundedRectangle(cornerRadius: 10)
                                               .stroke(Color.clear, lineWidth: 0)
                                :
                                RoundedRectangle(cornerRadius: 10)
                                 .stroke(Color.white, lineWidth: 1.5)


                               )
                            .padding(2)
                            .padding(.trailing)
                            .onTapGesture {
                                selectedFeatured = i
                                fetchFeatured(showID: selectedFeatured?.id ?? "")
                                let haptic = UIImpactFeedbackGenerator(style: .soft)
                                haptic.impactOccurred()
                            }

                    }
                    }
                }
            }
            Divider().background(Color.gray)
            ScrollView(.vertical, showsIndicators: true) {
                ForEach(featured, id: \.id) { i in
                    ContentRow(episode: i, streamer: streamer, paddingVertical: 10)
                    if featured.last?.id != i.id {
                        Divider().background(Color(.lightGray))
                    } else {
                        Divider().background(Color(.white))
                        HStack {
                        Text("\(selectedFeatured?.relationships.episodes.data.count ?? 0) Episodes Available")
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
                            selected = selectedFeatured
                            let haptic = UIImpactFeedbackGenerator(style: .soft)
                            haptic.impactOccurred()
                        }
                        Divider().background(Color(.white))

                    }
                }
            }
            .frame(height: DeviceType.deviceIsPad ? .infinity : 300)
            Spacer()
        }
        .padding(.leading)
        .padding(.top, 5)
        .foregroundColor(.white)
        .background(Color.white.opacity(0).ignoresSafeArea(.all))
        .onAppear {
            group.enter()
            podcastsAPI.getPodcasts { result in
                switch(result) {
                case .success(let podcasts):
                    self.podcasts = podcasts
                    selectedFeatured = podcasts.first
                    fetchFeatured(showID: selectedFeatured?.id ?? "")
        
                case .failure(let error):
                    print(error.localizedDescription )
                }
            }
            group.leave()
            AppReviewRequest.RequestReviewWhenNeeeded()
        }
        .redacted(reason: podcasts.isEmpty ? .placeholder : [])
        }
        .sheet(item: $selected) { podcast in
            ContentWrapper(streamer: streamer, navConfig: .detailConfig, navTitle: podcast.attributes.title) {   
                PodcastDetail(podcast: podcast, streamer: streamer, podcastAPI: podcastsAPI)
            }
        }
    }
    func fetchFeatured(showID: String) {
        group.enter()
        featured.removeAll()
        podcastsAPI.getEpisode(showID: showID) { result in
            switch(result) {
            case .success(let ep):
                ep.sorted(
                    by: { return $0.attributes.number > $1.attributes.number }
                ).forEach { ep in
                    if featured.count < 4 {
                        featured.append(ep)
                    }
                }
            case .failure(let error):
                print(error)
        }
        }
        group.leave()
    }
}


