//
//  RadioStreamer.swift
//  WPRK (iOS)
//
//  Created by Mwai Banda on 9/10/21.
//

import Foundation
import AVKit
import Combine
import SwiftUI

final class RadioStreamer: NSObject, ObservableObject {
    @Published var itemTitle: String = "Tune In..."
    @Published var isPlaying = false
    @Published var mediaURL = ""
    let streamingURL: URL
    let player: AVPlayer!
    private var playerItem: AVPlayerItem!
    private let currentDate: Date
    static let sharedInstance = RadioStreamer(streamingURL: URL(string: Constants.DEFAULT_STREAM)!)
    private init(streamingURL: URL) {
        self.streamingURL = streamingURL
        self.playerItem = AVPlayerItem(url: streamingURL)
        self.player = AVPlayer(playerItem: self.playerItem)
        self.currentDate = Date()
        super.init()
        let metaOutput = AVPlayerItemMetadataOutput(identifiers: nil)
        metaOutput.setDelegate(self, queue: DispatchQueue.main)
        self.playerItem?.add(metaOutput)
    }
    
    private func changeMedia() {
        let metaOutput = AVPlayerItemMetadataOutput(identifiers: nil)
        metaOutput.setDelegate(self, queue: DispatchQueue.main)
        self.playerItem?.add(metaOutput)
    }
    
    func switchToDefault(){
        if isPlaying {
            pauseStreaming()
            self.playerItem = AVPlayerItem(url: URL(string: Constants.DEFAULT_STREAM)!)
            self.player.replaceCurrentItem(with: self.playerItem)
            self.changeMedia()
            playStreaming()
        } else {
            self.playerItem = AVPlayerItem(url: URL(string: Constants.DEFAULT_STREAM)!)
            self.player.replaceCurrentItem(with: self.playerItem)
            self.changeMedia()
            playStreaming()
        }
    }
    func playStreaming() {
        self.player.play()
        self.isPlaying = true
        
    }
    func pauseStreaming() {
        self.player.pause()
        self.isPlaying = false
    }
    func getCurrentDayOfWeek() -> String {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "EEEE"
        let dayOfTheWeekString = dateFormatter.string(from: currentDate)
        return dayOfTheWeekString
    }
}

extension RadioStreamer: AVPlayerItemMetadataOutputPushDelegate {
    func metadataOutput(_ output: AVPlayerItemMetadataOutput, didOutputTimedMetadataGroups groups: [AVTimedMetadataGroup], from track: AVPlayerItemTrack?) {
        if  let item = groups.first?.items.last {
            let Song = (item.value(forKeyPath: "value")!)
            DispatchQueue.main.async {
                self.itemTitle = String(describing: Song)
            }
            print(Song)
        } else {
            print("MetaData Error")
        }
    }
}
