//
//  PodcastDetail.swift
//  WPRK (iOS)
//
//  Created by Mwai Banda on 10/2/21.
//

import SwiftUI
import SDWebImageSwiftUI
import AVKit

struct PodcastDetail: View {
    var podcast: Podcast
    @ObservedObject var streamer: RadioStreamer
    @ObservedObject var podcastAPI: ContentAPI
    @State private var  episodes = [Episode]()
    var body: some View {
        ScrollView {
            VStack(spacing: 0) {
                ImageCover(imageUrl: podcast.attributes.imageURL, category: podcast.attributes.category)
                

                VStack {
                    HStack {
                        VStack(alignment: .leading, spacing: 0) {
                        
                        Text(podcast.attributes.title)
                            .font(.title)
                            .bold()
                        ExpandableText(podcast.attributes.attributesDescription, lineLimit: 4)
                            .foregroundColor(.gray)
                    }.padding(.leading)
                    }

                    ForEach(episodes, id: \.id) { episode in
                        ContentRow(episode: episode, streamer: streamer, paddingHorizontal: 20, paddingVertical: 20)
                        
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
            podcastAPI.getEpisode(showID: podcast.id) { result in
                switch(result){
                case .success(let episodes):
                    self.episodes.append(contentsOf: episodes)
                case .failure(let err):
                    print(err.localizedDescription)
                }
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
