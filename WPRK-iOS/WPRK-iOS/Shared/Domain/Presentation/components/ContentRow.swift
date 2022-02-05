//
//  ContentRow.swift
//  WPRK (iOS)
//
//  Created by Mwai Banda on 10/8/21.
//

import SwiftUI
import AVFoundation

struct ContentRow: View {
    var episode: Episode?
    var category: String
    var title: String
    var description: String
    @ObservedObject var streamer: RadioStreamer
    var paddingHor: CGFloat
    var paddingVer: CGFloat
    
    init(
        episode: Episode? = nil,
        streamer: RadioStreamer? = nil,
        category: String = "",
        title: String = "",
        description: String = "",
        paddingHorizontal: CGFloat = 0,
        paddingVertical: CGFloat = 0
    ) {
        self.episode  = episode
        self.streamer = streamer ?? RadioStreamer.sharedInstance
        self.category = category
        self.title = title
        self.description = description
        self.paddingHor = paddingHorizontal
        self.paddingVer = paddingVertical
    }
    
    var body: some View {
        ZStack {
            if title.isEmpty {
                HStack {
                    VStack(alignment: .leading){
                        Text("EPISODE \(episode?.attributes.number ?? 0)")
                            .font(.caption)
                            .fontWeight(.light)
                        Text(episode?.attributes.title ?? "")
                            .foregroundColor(.white)
                            .bold()
                            .font(.headline)
                        ExpandableText(
                            episode?.attributes.attributesDescription.replacingOccurrences(of: "<div>", with: "")
                                .replacingOccurrences(of: "</div>", with: "")
                                .replacingOccurrences(of: "&nbsp;", with: "")
                                .replacingOccurrences(of: "<br>", with: "")
                                .replacingOccurrences(of: "&amp;", with: "")
                                .replacingOccurrences(of: "<em>", with: "")
                                .replacingOccurrences(of: "</em>", with: "")
                                .replacingOccurrences(of: "<strong>", with: "")
                                .replacingOccurrences(of: "</strong>", with: "")
                                ?? "", lineLimit: 4
                        ).padding(.bottom, 5)
                            .foregroundColor(.gray)

                            
                        HStack {
                            Image(systemName: "play.circle")
                            Text(episode?.attributes.durationInMmss ?? "")
                        }
                    }
                    Spacer()
                }.padding(.vertical, paddingVer)
                .padding(.horizontal, paddingHor)
                .onTapGesture {
                    let episodeItem = AVPlayerItem(url: URL(string: episode?.attributes.mediaURL ?? "")!)
                    streamer.player.replaceCurrentItem(with: episodeItem)
                    if !streamer.isPlaying {
                        streamer.playStreaming()
                    }
                    streamer.itemTitle = "EP \(episode?.attributes.number ?? 0) - " + (episode?.attributes.title ?? "")
                    streamer.mediaURL = episode?.attributes.imageURL ?? ""
                    let haptic = UINotificationFeedbackGenerator()
                    haptic.notificationOccurred(.success)
                }
            } else {
                HStack {
                    VStack(alignment: .leading, spacing: 0){
                        HStack {
                            HStack {
                            Circle()
                                .frame(width: 10, height: 10)
                                .foregroundColor(Color(hex: 0xffafcc))

                        Text(category)
                            .font(.caption)
                            .fontWeight(.light)
                            .foregroundColor(.gray)
                        }
                        .padding(.horizontal, 15)
                        .padding(.vertical, 10)
                        .overlay(
                               Capsule()
                                .stroke(Color.white, lineWidth: 1.5)
                           )
                            Spacer()
                          /* ADD: Actions */
                        }
                        .padding(.leading, 1)
                        .padding(.bottom)
                        Text(title)
                            .foregroundColor(.white)
                            .bold()
                            .font(.headline)
                        Text(description)
                            .lineLimit(nil)
                            .fixedSize(horizontal: false, vertical: true)
                            .frame(maxHeight: .infinity)
                            .foregroundColor(.gray)

                       
                    }
                    Spacer()
                }.padding(.vertical)
               
            }
        }
    }
}


