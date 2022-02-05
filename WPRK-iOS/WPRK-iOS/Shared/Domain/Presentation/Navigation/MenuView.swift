//
//  MenuView.swift
//  WPRK (iOS)
//
//  Created by Mwai Banda on 9/9/21.
//

import SwiftUI

struct MenuView: View {
    var body: some View {
        ZStack {
            LinearGradient(gradient: Gradient(colors: [.black, .gray]), startPoint: .top, endPoint: .bottom)
                .ignoresSafeArea(.all)

            VStack {
                Text("Hello, World!")
            }
        }
    }
}

struct MenuView_Previews: PreviewProvider {
    static var previews: some View {
        MenuView()
    }
}
