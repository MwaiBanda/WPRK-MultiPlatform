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

final class RadioStreamer:  AVPlayer, ObservableObject {
    @Published var placeholderTitle: String = "Tune In..."
    @Published var itemTitle: String = "Tune In..."
    @Published var displayTitle: String = "Tune In..."

    @Published var isPlaying = false
    @Published var isPlayingPodcast = false
    @Published var mediaURL = ""
    @Published var playValue: TimeInterval = 0.0
    var playerDuration: TimeInterval = 146
    var timer = Timer.publish(every: 1, on: .main, in: .common).autoconnect()
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
        registerObserves()
    }
    
    @Published var currentTimeInSeconds: Double = 0.0
      private var timeObserverToken: Any?
      // ... some other staff

      // MARK: Publishers
      var currentTimeInSecondsPass: AnyPublisher<Double, Never>  {
          return $currentTimeInSeconds
              .eraseToAnyPublisher()
      }

    private func changeMedia() {
        let metaOutput = AVPlayerItemMetadataOutput(identifiers: nil)
        metaOutput.setDelegate(self, queue: DispatchQueue.main)
        self.playerItem?.add(metaOutput)
    }
    func switchToURL(streamingURL: URL){
        
    }
    func switchToEpisode(episode: Episode?){
        let episodeItem = AVPlayerItem(url: URL(string: episode?.attributes.mediaURL ?? "")!)
        player.replaceCurrentItem(with: episodeItem)
        if !isPlaying {
            initiateStream()
        }
        itemTitle = "EP \(episode?.attributes.number ?? 0) - " + (episode?.attributes.title ?? "")
        displayTitle = "EP \(episode?.attributes.number ?? 0) - " + (episode?.attributes.title ?? "")
        mediaURL = episode?.attributes.imageURL ?? ""
        isPlayingPodcast = true
    }
    
    func switchToDefault(){
        if isPlaying {
            pauseStream()
            playerItem = AVPlayerItem(url: URL(string: Constants.DEFAULT_STREAM)!)
            player.replaceCurrentItem(with: self.playerItem)
            changeMedia()
            initiateStream()
        } else {
            playerItem = AVPlayerItem(url: URL(string: Constants.DEFAULT_STREAM)!)
            player.replaceCurrentItem(with: self.playerItem)
            changeMedia()
            initiateStream()
        }
        playValue = 0.0
        isPlayingPodcast = false
    }
    func initiateStream() {
        player.play()
        isPlaying = true
        displayTitle = itemTitle
        
    }
    func pauseStream() {
        displayTitle = placeholderTitle
        player.pause()
        isPlaying = false
    }

    func getCurrentDayOfWeek() -> String {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "EEEE"
        let dayOfTheWeekString = dateFormatter.string(from: currentDate)
        return dayOfTheWeekString
    }

    

      private func registerObserves() {

          let interval = CMTime(seconds: 1, preferredTimescale: CMTimeScale(NSEC_PER_SEC))
          timeObserverToken = self.addPeriodicTimeObserver(forInterval: interval, queue: .main) {
              [weak self] _ in
              self?.currentTimeInSeconds = self?.currentTime().seconds ?? 0.0
          }

      }

      // func for rewind song time
      func rewindTime(to seconds: Double) {
          let timeCM = CMTime(seconds: seconds, preferredTimescale: CMTimeScale(NSEC_PER_SEC))
          self.seek(to: timeCM)
      }

      // sure I need to remove observer:
      deinit {

          if let token = timeObserverToken {
              self.removeTimeObserver(token)
              timeObserverToken = nil
          }

      }
}

extension RadioStreamer: AVPlayerItemMetadataOutputPushDelegate {
    func metadataOutput(_ output: AVPlayerItemMetadataOutput, didOutputTimedMetadataGroups groups: [AVTimedMetadataGroup], from track: AVPlayerItemTrack?) {
        if  let item = groups.first?.items.last {
            let Song = (item.value(forKeyPath: "value")!)
            DispatchQueue.main.async {
                self.itemTitle = String(describing: Song)
                self.displayTitle = String(describing: Song)
            }
            print(Song)
        } else {
            print("MetaData Error")
        }
    }
}


