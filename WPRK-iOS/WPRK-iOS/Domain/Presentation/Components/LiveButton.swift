//
//  LiveButton.swift
//  WPRK (iOS)
//
//  Created by Mwai Banda on 10/9/21.
//

import SwiftUI

struct LiveButton: View {
    @ObservedObject var streamer: WPRKStreamer
    var body: some View {
        VStack {
            HStack {
                HStack {
            Text("91.5").fontWeight(.heavy) +
            Text("FM")
                .fontWeight(.heavy)
                .font(.caption)
                }
                .lineLimit(1)
                .fixedSize(horizontal: false, vertical: true)
            Image(systemName: "livephoto.play")
                .foregroundColor(.red)
            }
           
        }
        .padding()
        .padding(.horizontal, 8)
        .overlay(
               RoundedRectangle(cornerRadius: 10)
                .stroke(Color.white, lineWidth: 1.5)
           )
        .padding(5)
        .onTapGesture {
            DispatchQueue.main.async {
                streamer.switchToDefault()
            }
            let haptic = UINotificationFeedbackGenerator()
            haptic.notificationOccurred(.success)
        }
    }
}

struct LiveButton_Previews: PreviewProvider {
    static var previews: some View {
        LiveButton(streamer: WPRKStreamer.sharedInstance)
    }
}
