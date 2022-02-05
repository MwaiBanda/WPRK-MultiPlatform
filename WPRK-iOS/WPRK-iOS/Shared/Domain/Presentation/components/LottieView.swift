//
//  LottieView.swift
//  Odemmer
//
//  Created by Mwai Banda on 1/25/21.
//

import SwiftUI
import Lottie

struct LottieView: UIViewRepresentable {
    typealias UIViewType = UIView
    var name: String
    var loopMode: LottieLoopMode?
    var alignment: UIView.ContentMode?
    func makeUIView(context: UIViewRepresentableContext<LottieView>) -> UIView {
        let view = UIView(frame: .zero)
        
        let animationView = AnimationView()
        let animation  = Animation.named(name)
        animationView.animation = animation
        animationView.contentMode = alignment ?? .scaleAspectFit 
        animationView.loopMode = loopMode ?? .playOnce
        animationView.play()
        
        animationView.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(animationView)
        NSLayoutConstraint.activate([
            animationView.heightAnchor.constraint(equalTo: view.heightAnchor),
            animationView.widthAnchor.constraint(equalTo: view.widthAnchor)
        ])
        return view
    }
    
    func updateUIView(_ uiView: UIView, context: UIViewRepresentableContext<LottieView>) {
        
    }
}
