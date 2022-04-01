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
import MediaPlayer

final class WPRKStreamer:  AVPlayer, ObservableObject {
    @Published var placeholderTitle: String = "Tune In..."
    @Published var itemTitle: String = "Tune In..."
    @Published var displayTitle: String = "Tune In..."
    @Published var showTitle: String = ""
    
    @Published var isPlaying = false
    @Published var isPlayingPodcast = false
    @Published var mediaURL = ""
    @Published var playValue: TimeInterval = 0.0
    
    
    private var timer = Timer.publish(every: 1, on: .main, in: .common).autoconnect()
    private let streamingURL: URL
    private let player: AVPlayer!
    private var playerItem: AVPlayerItem!
    private let currentDate: Date
    
    
    static let sharedInstance = WPRKStreamer(streamingURL: URL(string: Constants.DEFAULT_STREAM)!)
    
    
    private init(streamingURL: URL) {
        self.streamingURL = streamingURL
        self.playerItem = AVPlayerItem(url: streamingURL)
        self.player = AVPlayer(playerItem: self.playerItem)
        self.currentDate = Date()
        super.init()
        let metaOutput = AVPlayerItemMetadataOutput(identifiers: nil)
        metaOutput.setDelegate(self, queue: DispatchQueue.main)
        self.playerItem?.add(metaOutput)
        setupRemoteTransportControls()
        setupNowPlaying()
        setupNotifications()
    }
    
    
    private func changeMedia() {
        let metaOutput = AVPlayerItemMetadataOutput(identifiers: nil)
        metaOutput.setDelegate(self, queue: DispatchQueue.main)
        self.playerItem?.add(metaOutput)
    }
    
    
    func switchToEpisode(episode: Episode?, show: String){
        let episodeItem = AVPlayerItem(url: URL(string: episode?.attributes.mediaURL ?? "")!)
        player.replaceCurrentItem(with: episodeItem)
        if !isPlaying {
            initiateStream()
        }
        itemTitle = "EP \(episode?.attributes.number ?? 0) - " + (episode?.attributes.title ?? "")
        displayTitle = "EP \(episode?.attributes.number ?? 0) - " + (episode?.attributes.title ?? "")
        mediaURL = episode?.attributes.imageURL ?? ""
        isPlayingPodcast = true
        updateNowPlaying(isPause: false, title: show)
        showTitle = show
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
        updateNowPlaying(isPause: false)
        showTitle = ""
        
    }
    
    
    func initiateStream() {
        player.play()
        isPlaying = true
        displayTitle = itemTitle
        if showTitle.isEmpty {
            updateNowPlaying(isPause: true)
        } else {
            updateNowPlaying(isPause: true, title: showTitle)
        }
    }
    
    
    func pauseStream() {
        displayTitle = placeholderTitle
        player.pause()
        isPlaying = false
        if showTitle.isEmpty {
            updateNowPlaying(isPause: true)
        } else {
            updateNowPlaying(isPause: true, title: showTitle)
        }
    }
    
    
    func getCurrentDayOfWeek() -> String {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "EEEE"
        let dayOfTheWeekString = dateFormatter.string(from: currentDate)
        return dayOfTheWeekString
    }
    
    
    
    func setupRemoteTransportControls() {
        // Get the shared MPRemoteCommandCenter
        let commandCenter = MPRemoteCommandCenter.shared()
        
        // Add handler for Play Command
        commandCenter.playCommand.addTarget { [unowned self] event in
            print("Play command - is playing: \(self.isPlaying)")
            if !self.isPlaying {
                self.initiateStream()
                return .success
            }
            return .commandFailed
        }
        
        // Add handler for Pause Command
        commandCenter.pauseCommand.addTarget { [unowned self] event in
            print("Pause command - is playing: \(self.isPlaying)")
            if self.isPlaying {
                self.pauseStream()
                return .success
            }
            return .commandFailed
        }
    }
    
    
    func setupNowPlaying() {
        var nowPlayingInfo = [String : Any]()
        
        // Define Now Playing Info
        nowPlayingInfo[MPMediaItemPropertyTitle] =  "Tune In..."
        
        if let image = UIImage(named: "WPRKBlack") {
            nowPlayingInfo[MPMediaItemPropertyArtwork] = MPMediaItemArtwork(boundsSize: image.size) { size in
                return image
            }
        }
        nowPlayingInfo[MPNowPlayingInfoPropertyElapsedPlaybackTime] = player.currentTime
        nowPlayingInfo[MPMediaItemPropertyPlaybackDuration] = player.currentItem?.duration
        nowPlayingInfo[MPNowPlayingInfoPropertyPlaybackRate] = player.rate
        
        // Set the metadata
        MPNowPlayingInfoCenter.default().nowPlayingInfo = nowPlayingInfo
    }
    
    
    func updateNowPlaying(isPause: Bool, title: String? = nil) {
        // Define Now Playing Info
        var nowPlayingInfo = MPNowPlayingInfoCenter.default().nowPlayingInfo!
        nowPlayingInfo[MPMediaItemPropertyArtist] = title ?? "WPRK 91.5FM"
        nowPlayingInfo[MPMediaItemPropertyTitle] = displayTitle
        nowPlayingInfo[MPNowPlayingInfoPropertyElapsedPlaybackTime] = player.currentTime
        nowPlayingInfo[MPNowPlayingInfoPropertyPlaybackRate] = isPause ? 0 : 1
        
        // Set the metadata
        MPNowPlayingInfoCenter.default().nowPlayingInfo = nowPlayingInfo
    }
    
    
    @objc func handleInterruption(notification: Notification) {
        guard let userInfo = notification.userInfo,
              let typeValue = userInfo[AVAudioSessionInterruptionTypeKey] as? UInt,
              let type = AVAudioSession.InterruptionType(rawValue: typeValue) else {
                  return
              }
        
        if type == .began {
            print("Interruption began")
            // Interruption began, take appropriate actions
        }
        else if type == .ended {
            if let optionsValue = userInfo[AVAudioSessionInterruptionOptionKey] as? UInt {
                let options = AVAudioSession.InterruptionOptions(rawValue: optionsValue)
                if options.contains(.shouldResume) {
                    // Interruption Ended - playback should resume
                    print("Interruption Ended - playback should resume")
                    initiateStream()
                } else {
                    // Interruption Ended - playback should NOT resume
                    print("Interruption Ended - playback should NOT resume")
                }
            }
        }
    }
    
    
    @objc func handleRouteChange(notification: Notification) {
        guard let userInfo = notification.userInfo,
              let reasonValue = userInfo[AVAudioSessionRouteChangeReasonKey] as? UInt,
              let reason = AVAudioSession.RouteChangeReason(rawValue:reasonValue) else {
                  return
              }
        switch reason {
        case .newDeviceAvailable:
            let session = AVAudioSession.sharedInstance()
            for output in session.currentRoute.outputs where output.portType == AVAudioSession.Port.headphones {
                print("headphones connected")
                DispatchQueue.main.sync {
                    initiateStream()
                }
                break
            }
        case .oldDeviceUnavailable:
            if let previousRoute =
                userInfo[AVAudioSessionRouteChangePreviousRouteKey] as? AVAudioSessionRouteDescription {
                for output in previousRoute.outputs where output.portType == AVAudioSession.Port.headphones {
                    print("headphones disconnected")
                    DispatchQueue.main.sync {
                        pauseStream()
                    }
                    break
                }
            }
        default: ()
        }
    }
    
    
    func setupNotifications() {
        let notificationCenter = NotificationCenter.default
        notificationCenter.addObserver(self,
                                       selector: #selector(handleInterruption),
                                       name: AVAudioSession.interruptionNotification,
                                       object: nil)
        notificationCenter.addObserver(self,
                                       selector: #selector(handleRouteChange),
                                       name: AVAudioSession.routeChangeNotification,
                                       object: nil)
    }
    
    
}

extension WPRKStreamer: AVPlayerItemMetadataOutputPushDelegate {
    func metadataOutput(_ output: AVPlayerItemMetadataOutput, didOutputTimedMetadataGroups groups: [AVTimedMetadataGroup], from track: AVPlayerItemTrack?) {
        if  let item = groups.first?.items.last {
            let Song = (item.value(forKeyPath: "value")!)
            DispatchQueue.main.async { [self] in
                self.itemTitle = String(describing: Song)
                self.displayTitle = String(describing: Song)
                self.updateNowPlaying(isPause: false)
                
            }
            print(Song)
        } else {
            print("MetaData Error")
        }
    }
}


