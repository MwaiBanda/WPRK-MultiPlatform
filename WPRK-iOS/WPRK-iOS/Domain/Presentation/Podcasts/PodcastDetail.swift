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

struct PodcastDetail: View {
    var podcast: Podcast
    @ObservedObject var streamer: WPRKStreamer
    @ObservedObject var podcastViewModel: PodcastViewModel
    var body: some View {
        ScrollView(showsIndicators: false) {
            VStack(spacing: 0) {
                ImageCover(imageUrl: podcast.thumbnailURL, category: "WPRK")
                
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

                    ForEach(podcastViewModel.episodes, id: \.id) { episode in
                        ContentRow(showTitle: podcast.title ,episode: episode, streamer: streamer, paddingHorizontal: 20, paddingVertical: 20)
                        
                        Divider()
                            .background(Color.gray)
                    }
                    Spacer()
                }
                .frame(maxHeight: .infinity)
                .ignoresSafeArea(.all, edges: .bottom)
                
            }
   
        }
        .foregroundColor(.white)
        .onAppear {
            Task.detached {
                await podcastViewModel.getEpisodes(showID: podcast.id)
            }
        }
    }
}




struct ImageCover: View {
    var imageUrl : String
    var category: String
    var body: some View {
        ZStack {
            WebImage(url: URL(string: imageUrl))
                .resizable()
                .aspectRatio(contentMode: .fill)
            Color.black
            WebImage(url: URL(string: imageUrl))
                .resizable()
                .aspectRatio(contentMode: .fill)
                .mask(LinearGradient(gradient: Gradient(stops: [.init(color: .clear, location: 0.2), .init(color: .black, location: 0.6)]), startPoint: .bottom, endPoint: .top))
            VStack {
                Spacer()
                HStack {
                    Text(category)
                        .foregroundColor(.black)
                        .padding(.vertical, 10)
                        .padding(.horizontal, 15)
                        .background(Color.white)
                        .cornerRadius(10)
                        .padding(.leading)
                    Spacer()
                    
                }
            }.frame(maxHeight: 330)
            
        }
        .frame(maxHeight: 350)
        .clipped()
    }
}
