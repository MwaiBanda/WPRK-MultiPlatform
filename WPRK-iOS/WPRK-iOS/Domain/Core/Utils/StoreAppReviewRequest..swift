//
//  AppReviewRequest.swift
//  Odemmer
//
//  Created by Mwai Banda on 1/9/21.
//

import SwiftUI
import StoreKit

enum AppReviewRequest {
    static var threshold = 12
    @AppStorage("runSinceLastRequest")  static var runSinceLastRequest = 0
    @AppStorage("version")  static var version = ""
    
    static func RequestReviewWhenNeeeded () {
        runSinceLastRequest += 1
        let appBuild = Bundle.main.object(forInfoDictionaryKey: "CFBundleVersion") as! String
        let appVersion = Bundle.main.object(forInfoDictionaryKey: "CFBundleShortVersionString") as! String
        let thisVersion = "\(appVersion) build: \(appBuild)"
        
        if thisVersion != version {
            if runSinceLastRequest >= threshold {
                if let scene = UIApplication.shared.connectedScenes.first(where: {$0.activationState == .foregroundActive}) as? UIWindowScene {
                    SKStoreReviewController.requestReview(in: scene)
                    version = thisVersion
                    runSinceLastRequest = 0
                }
                
            }
            
        } else {
            runSinceLastRequest = 0
        }
        
    }
}
