//
//  WPRK_iOSApp.swift
//  WPRK-iOS
//
//  Created by Mwai Banda on 2/4/22.
//

import SwiftUI

@main
struct WPRK_iOSApp: App {
    @StateObject private var networkService = NetworkService.sharedInstance
    init() {
        authorizeUserNotifications()
    }
    var body: some Scene {
        WindowGroup {
            ContentView(networkService: networkService)
        }
    }
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
