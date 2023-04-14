//
//  PodcastDetail.swift
//  WPRK (iOS)
//
//  Created by Mwai Banda on 10/2/21.
//

import SwiftUI
import SDWebImageSwiftUI
import AVKit
import WPRKSDK
import Combine

struct PodcastDetail: View {
    var podcast: Podcast
    @ObservedObject var streamer: WPRKStreamer
    @ObservedObject var podcastViewModel: PodcastViewModel
    @State private var disposables = Set<AnyCancellable>()
    
    var body: some View {
        ScrollView(showsIndicators: false) {
            VStack(spacing: 0) {
                HeroImageGradient(imageUrl: podcast.thumbnailURL, category: "WPRK")
                
                VStack {
                    HStack {
                        VStack(alignment: .leading, spacing: 0) {
                            
                            Text(podcast.title)
                                .font(.title)
                                .bold()
                            ExpandableText(podcast.description_                                       .trimmingCharacters(in: .whitespacesAndNewlines)
                                           , lineLimit: 4)
                            .foregroundColor(.gray)
                        }.padding(.leading)
                    }
                    
                    ForEach(podcastViewModel.episodes, id: \.self) { episode in
                        ContentRow(showTitle: podcast.title ,episode: episode, streamer: streamer, paddingHorizontal: 20, paddingVertical: 20)
                        
                        Divider()
                            .background(Color.gray)
                    }
                    if podcastViewModel.canLoadMore && podcastViewModel.episodes.count > 9 && (podcastViewModel.episodes.last?.number ?? 0) > 1 {
                        Button { podcastViewModel.getEpisodes(showID: podcast.id) } label: {
                            Text("Load more...")
                                .font(.headline)
                                .fontWeight(.heavy)
                                .foregroundColor(Color(hex: 0xffafcc))
                                .padding(.top)
                        }
                    }
                    Spacer()
                }
                .frame(maxHeight: .infinity)
                .ignoresSafeArea(.all, edges: .bottom)
            }
        }
        .foregroundColor(.white)
        .onAppear {
            podcastViewModel.currentPage = 1
            podcastViewModel.getEpisodes(showID: podcastViewModel.selected?.id ?? ""){
                let timer = Timer.publish(every: 5, on: .main, in: .common)
                    .autoconnect()
                timer
                    .prepend(Date())
                    .map { _ in }
                    .sink(receiveValue: { _ in
                        if podcastViewModel.episodes.isEmpty && podcastViewModel.selected?.title != Constants.ANNIVERSARY {
                            podcastViewModel.getEpisodes(showID: podcastViewModel.selected?.id ?? "")
                            podcastViewModel.currentPage = 1
                            print("timer[RETRY] - hit")
                        } else {
                            timer.upstream.connect().cancel()
                            print("timer[RETRY] - cancelled")
                        }
                    })
                    .store(in: &disposables)
            }
        }
    }
}





