//
//  Player.swift
//  WPRK (iOS)
//
//  Created by Mwai Banda on 9/10/21.
//

import Foundation
import SwiftUI
import SDWebImageSwiftUI
import AVFoundation

struct PlayerView: View {
    @ObservedObject var streamer: WPRKStreamer
   @State private var playing = false

    var body: some View {
        HStack(alignment: .center){
         Image("WPRKWhite")
                .resizable()
                .frame(width: 46, height: 46, alignment: .center)
                .cornerRadius(10)
                .padding([.top, .bottom], 10)
                .padding(.leading, 8)
                .offset(y: 3)

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
                .offset(y: 12)
                VStack {
                    MarqueeText(
                        text: $streamer.displayTitle)
                        .foregroundColor(.white)
                }
                Spacer()
            }
            
            Spacer()
            HStack {
             
                Button(action: {
                    DispatchQueue.main.async {
                        if streamer.isPlaying {
                            playing = false
                            streamer.pauseStream()
                        } else {
                            playing = true
                            streamer.initiateStream()
                        }
                    }
                    let haptic = UIImpactFeedbackGenerator(style: .rigid)
                    haptic.impactOccurred()
                }){
                    Image(systemName: playing ? "pause.circle.fill" :
                            "play.circle.fill")
                        .resizable()
                        .foregroundColor(.white)
                        .frame(width: 37, height: 37)
                }.padding(.trailing)
            }
        }
        .frame(maxWidth: .infinity, maxHeight: 65)
        .background(Color(hex: 0xffafcc))
        .cornerRadius(10)
        .overlay(
               RoundedRectangle(cornerRadius: 10)
                .stroke(Color.white, lineWidth: 1.5)
           )
        .padding(.horizontal,1)
        .padding(.bottom, 2)
        .onAppear {
            playing = streamer.isPlaying
        }
        .onDisappear {
            playing = streamer.isPlaying
        }
        .onChange(of: streamer.isPlaying) { newValue in
            playing = newValue
        }
    }
}
