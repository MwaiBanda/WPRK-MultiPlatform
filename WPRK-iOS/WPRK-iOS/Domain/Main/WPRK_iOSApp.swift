//
//  WPRK_iOSApp.swift
//  WPRK-iOS
//
//  Created by Mwai Banda on 2/4/22.
//
import Foundation
import SwiftUI
import WPRKSDK
import Firebase
import AVFoundation
import SDWebImageSwiftUI

@main
struct WPRK_iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate
    @StateObject private var networkService = NetworkService.sharedInstance
    @StateObject private var podcastViewModel = PodcastViewModel(contentService: ContentServiceImplementation.sharedInstance, group: DispatchGroup())
    var body: some Scene {
        WindowGroup {
            ContentView(networkService: networkService, podcastViewModel: podcastViewModel)
        }
    }

}
class AppDelegate : NSObject, UIApplicationDelegate {
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        FirebaseApp.configure()
        DependencyRegistryKt.doInitKoin()
        authorizeUserNotifications()
        do {
            try AVAudioSession.sharedInstance().setCategory(AVAudioSession.Category.playback, mode: AVAudioSession.Mode.default)
            try
                AVAudioSession.sharedInstance().setActive(true)
        }
        catch {
            print("Setting category to AVAudioSessionCategoryPlayback failed.")
        }
        SDWebImageDownloader
            .shared
            .setValue("image/webp,image/apng,image/*,*/*;q=0.8", forHTTPHeaderField: "Accept")
        Thread.sleep(forTimeInterval: 0.5)
        return true
    }
    
    
    // MARK: UISceneSession Lifecycle
    
    func application(_ application: UIApplication, configurationForConnecting connectingSceneSession: UISceneSession, options: UIScene.ConnectionOptions) -> UISceneConfiguration {
        // Called when a new scene session is being created.
        // Use this method to select a configuration to create the new scene with.
        return UISceneConfiguration(name: "Default Configuration", sessionRole: connectingSceneSession.role)
    }
    
    func application(_ application: UIApplication, didDiscardSceneSessions sceneSessions: Set<UISceneSession>) {
        // Called when the user discards a scene session.
        // If any sessions were discarded while the application was not running, this will be called shortly after application:didFinishLaunchingWithOptions.
        // Use this method to release any resources that were specific to the discarded scenes, as they will not return.
    }
    
}

extension AppDelegate {
    func authorizeUserNotifications() {
        print("AppDelegate ", #function)
        let center = UNUserNotificationCenter.current()
        center.requestAuthorization(options: [.alert, .badge, .sound]){
            (granted, error) in
            if granted {
                print(#function, "Authorized for User Notifications")
            } else {
                print("Not Authorized for User Notifications")
            }
        }
    }
}
