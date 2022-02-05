//
//  DeviceSizes.swift
//  WPRK (iOS)
//
//  Created by Mwai Banda on 10/9/21.
//

import Foundation
import SwiftUI

struct ScreenSize {
    static let width = UIScreen.main.bounds.size.width
    static let height = UIScreen.main.bounds.size.height
    static let maxLength = max(ScreenSize.width, ScreenSize.height)
    static let minLength = min(ScreenSize.width, ScreenSize.height)
    static let frame = CGRect(x: 0, y: 0, width: ScreenSize.width, height: ScreenSize.height)
}

struct DeviceType {
    static let iPhone4orLess = UIDevice.current.userInterfaceIdiom == .phone && ScreenSize.maxLength < 568.0
    static let iPhone5orSE = UIDevice.current.userInterfaceIdiom == .phone && ScreenSize.maxLength == 568.0
    static let iPhone678 = UIDevice.current.userInterfaceIdiom == .phone && ScreenSize.maxLength == 667.0
    static let iPhone678p = UIDevice.current.userInterfaceIdiom == .phone && ScreenSize.maxLength == 736.0
    static let iPhoneX = UIDevice.current.userInterfaceIdiom == .phone && ScreenSize.maxLength == 812.0
    static let iPhone11 = UIDevice.current.userInterfaceIdiom == .phone && ScreenSize.maxLength ==  896.0
    static let iPhone12 = UIDevice.current.userInterfaceIdiom == .phone && ScreenSize.maxLength ==  844.0
    static let iPhone12ProMax = UIDevice.current.userInterfaceIdiom == .phone && ScreenSize.maxLength > 895.0
    static let deviceIsPad = UIDevice.current.userInterfaceIdiom == .pad
    static let IS_IPAD              = UIDevice.current.userInterfaceIdiom == .pad && ScreenSize.maxLength > 1024.0
    static let IS_IPAD_PRO          = UIDevice.current.userInterfaceIdiom == .pad && ScreenSize.maxLength == 1366.0
}
