//
//  Player.swift
//  WPRK (iOS)
//
//  Created by Mwai Banda on 9/10/21.
//

import Foundation
import SwiftUI
import SDWebImageSwiftUI

struct PlayerView: View {
    @ObservedObject var streamer: RadioStreamer
    var body: some View {
        HStack{
         Image("WPRKWhite")
                .resizable()
                .frame(width: 50, height: 50, alignment: .center)
                .cornerRadius(10)
                .padding()
            .offset(y: 4)

            VStack {
                HStack {
                    Text("WPRK 91.5")
                        .foregroundColor(.white)
                        .font(.headline)
                        .fontWeight(.heavy) +
                        Text("FM")
                        .foregroundColor(.white)
                        .font(.caption2)
                        .fontWeight(.heavy)
                 Spacer()
                    
                }
                .offset(y: 14)
                VStack {
                    MarqueeText(
                        text: streamer.isPlaying ? $streamer.itemTitle : .constant("Tune In..."))
                        .foregroundColor(.white)
            
                }
            }
            
            Spacer()
            HStack {
             
                Button(action: {
                    DispatchQueue.main.async {
                        if streamer.isPlaying {
                            streamer.pauseStreaming()
                        } else {
                            streamer.playStreaming()
                        }
                    }
                    let haptic = UIImpactFeedbackGenerator(style: .rigid)
                    haptic.impactOccurred()
                }){
                    Image(systemName: streamer.isPlaying ? "pause.circle.fill" :
                            "play.circle.fill")
                        .resizable()
                        .foregroundColor(.white)
                        .frame(width: 45, height: 45)
                }.padding(.trailing)
            }
        }
        .frame(maxWidth: screenBounds.width - 20, maxHeight: 70)
        .background(Color(hex: 0xffafcc))
        .cornerRadius(10)
        .overlay(
               RoundedRectangle(cornerRadius: 10)
                .stroke(Color.white, lineWidth: 1.5)
           )
        .padding(.bottom, 2)
    }
}
