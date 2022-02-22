//
//  ConnectivityWrapper.swift
//  Virtual Groceries
//
//  Created by Mwai Banda on 9/3/21.
//

import Foundation
import SwiftUI

struct ConnectivityWrapper<Content: View>: View {
    @ObservedObject var networkService: NetworkService
    var content: () -> (Content)
    
    var body: some View {
        ZStack {
            Group {
                VStack {
                    LottieView(name: "noConnection", loopMode: .autoReverse, alignment: .scaleAspectFit)
                        .frame(width: 250, height: 250)
                        .padding()
                    Text("No Internet Access")
                        .font(.headline)
                        .bold()
                        .foregroundColor(.accentColor)
                    Text("Make sure WiFi or Cellular data is \nturned on then try again.")
                        .font(.subheadline)
                        .multilineTextAlignment(.center)
                        .padding(.horizontal)
                   
                }
                if networkService.hasInternetAccess {
                    content()
                }
            }
        }
    }
}
