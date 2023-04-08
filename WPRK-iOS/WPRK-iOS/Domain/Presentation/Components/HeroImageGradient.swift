//
//  HeroImageGradient.swift
//  WPRK-iOS
//
//  Created by Mwai Banda on 4/7/23.
//

import SwiftUI
import SDWebImageSwiftUI

struct HeroImageGradient: View {
    var imageUrl : String
    var category: String
    var body: some View {
        ZStack {
            WebImage(url: URL(string: imageUrl))
                .resizable()
                .aspectRatio(contentMode: .fill)
            Color.black
            WebImage(url: URL(string: imageUrl))
                .resizable()
                .aspectRatio(contentMode: .fill)
                .mask(LinearGradient(gradient: Gradient(stops: [.init(color: .clear, location: 0.2), .init(color: .black, location: 0.6)]), startPoint: .bottom, endPoint: .top))
            VStack {
                Spacer()
                HStack {
                    Text(category)
                        .foregroundColor(.black)
                        .padding(.vertical, 10)
                        .padding(.horizontal, 15)
                        .background(Color.white)
                        .cornerRadius(10)
                        .padding(.leading)
                    Spacer()
                    
                }
            }.frame(maxHeight: 330)
            
        }
        .frame(maxHeight: 350)
        .clipped()
    }
}


