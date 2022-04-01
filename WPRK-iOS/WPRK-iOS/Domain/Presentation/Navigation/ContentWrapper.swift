//
//  Live.swift
//  WPRK (iOS)
//
//  Created by Mwai Banda on 9/9/21.
//

import SwiftUI
import SwiftUI
import StoreKit



struct ContentWrapper<Content: View>: View {
    @ObservedObject var streamer: WPRKStreamer
    @State var showMenu = false
    var navTitle: String
    var navConfig: NavConfig
    var content: () -> (Content)
    init(
        streamer: WPRKStreamer, 
        navConfig: NavConfig = .defaultConfig,
        navTitle: String = "",
        @ViewBuilder content: @escaping () -> (Content)
    ) {
        self.streamer = streamer
        self.navTitle = navTitle
        self.content = content
        self.navConfig = navConfig
    }
    
    var body: some View {
        let drag = DragGesture().onEnded {
            if $0.translation.width < -100 {
                withAnimation(Animation.easeInOut(duration: 0.5)) {
                    self.showMenu = false
                    
                }
            }
        }
        VStack {
            ZStack{
                withAnimation {
                    ZStack {
                        Color(.black).ignoresSafeArea(.all)
                        GeometryReader { geometry in
                            VStack(spacing: 0){
                                NavBar(showMenu: $showMenu, navTitle: navTitle, navConfig: navConfig)
                                    .frame(minHeight: 50)
                                VStack(spacing: 0){
                                    content()
                                        .offset(y: -5)
                                    PlayerView(streamer: streamer)
                                }
                                .background(
                                    Color.black
                                )
                            }
                    
                            .frame(width: geometry.size.width, height: geometry.size.height)
                            .offset(x: self.showMenu ? geometry.size.width/1.45 : 0)
                            
                            .disabled(self.showMenu ? true : false)
                            
                            
                            if self.showMenu {
                                MenuView()
                                    .animation(.easeInOut(duration: 0.5))
                                    .frame(width: geometry.size.width/1.45)
                                    .transition(.move(edge: .leading))
                            }
                        }
                    }
                    .gesture(drag)
                    .onTapGesture {
                        withAnimation(Animation.easeInOut(duration: 0.5)) {
                            self.showMenu = false
                            
                            let haptic = UIImpactFeedbackGenerator(style: .light)
                            haptic.impactOccurred()
                        }
                    }
                }
                
            }
            .navigationBarBackButtonHidden(true)
            .navigationBarHidden(true)
        }
       
    }
}


//struct MenuWrapper_Previews: PreviewProvider {
//    static var previews: some View {
//        MenuWrapper {
//            Text("Home")
//        }
//    }
//}
