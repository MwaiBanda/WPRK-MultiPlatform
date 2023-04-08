//
//  TitledButtonTabRow.swift
//  WPRK-iOS
//
//  Created by Mwai Banda on 4/7/23.
//

import SwiftUI
import WPRKSDK

struct TitledButtonTabRow: View {
    var podcasts: [Podcast]
    var selectedFeatured: Podcast? = nil
    var onTabSelected: (Podcast) -> Void
    var body: some View {
        
        ScrollViewReader { value in
            ScrollView(.horizontal, showsIndicators: false) {
                HStack {
                    if podcasts.isEmpty {
                        ForEach(0..<5, id: \.self) { i in
                            VStack {
                                Text("Lorem ipsum")
                                    .fontWeight(.heavy)
                            }
                            .padding()
                            .padding(.horizontal)
                            .background(Color(hex: 0xffafcc))
                            .cornerRadius(10)
                            .overlay(RoundedRectangle(cornerRadius: 10).stroke(Color.white, lineWidth: 1.5))
                            .padding(2)
                            .padding(.trailing)
                            .redacted(reason: .placeholder)
                        }
                    } else {
                        ForEach(podcasts.filter({ $0.title != Constants.ANNIVERSARY }), id: \.id) { i in
                            HStack {
                                VStack {
                                    Text(i.title)
                                        .fontWeight(.heavy)
                                    
                                }
                                .padding()
                                .padding(.horizontal)
                                .background(i.title == selectedFeatured?.title  ? Color.clear : Color(hex: 0xffafcc))
                                .cornerRadius(10)
                                .overlay(i.title == selectedFeatured?.title ? RoundedRectangle(cornerRadius: 10).stroke(Color.clear, lineWidth: 1.5) : RoundedRectangle(cornerRadius: 10).stroke(Color.white, lineWidth: 1.5))
                                .padding(2)
                                .padding(.trailing)
                                .onTapGesture {
                                    onTabSelected(i)
                                    withAnimation(.easeIn(duration: 0.28)) {
                                        if i != podcasts.last {
                                            value.scrollTo(i.title , anchor: .center)
                                        } else {
                                            value.scrollTo(i.title, anchor: .trailing)
                                        }
                                    }
                                }
                                
                            }.id(i.title)
                        }
                    }
                }
                
            }
        }
    }
}
struct TitledButtonTabRow_Previews: PreviewProvider {
    static var previews: some View {
        TitledButtonTabRow(podcasts: [], onTabSelected: { _ in})
    }
}
