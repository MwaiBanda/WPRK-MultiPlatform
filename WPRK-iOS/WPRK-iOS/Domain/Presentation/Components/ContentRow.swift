//
//  ContentRow.swift
//  WPRK (iOS)
//
//  Created by Mwai Banda on 10/8/21.
//

import SwiftUI
import AVFoundation

struct ContentRow: View {
    var showTitle: String?
    var episode: Episode?
    var business: BusinessDeal?
    @ObservedObject var streamer: WPRKStreamer
    var paddingHor: CGFloat
    var paddingVer: CGFloat
    
    init(
        showTitle: String? = "",
        episode: Episode? = nil,
        streamer: WPRKStreamer? = nil,
        business: BusinessDeal? = nil,
        paddingHorizontal: CGFloat = 0,
        paddingVertical: CGFloat = 0
    ) {
        self.showTitle = showTitle
        self.episode  = episode
        self.streamer = streamer ?? WPRKStreamer.sharedInstance
        self.business = business
        self.paddingHor = paddingHorizontal
        self.paddingVer = paddingVertical
    }
 
    var body: some View {
        ZStack {
            if (episode != nil) {
                HStack {
                    VStack(alignment: .leading){
                        Text("EPISODE \(episode?.attributes.number ?? 0)")
                            .font(.caption)
                            .fontWeight(.light)
                        Text(episode?.attributes.title ?? "")
                            .foregroundColor(.white)
                            .bold()
                            .font(.headline)
                        ExpandableText(
                            episode?.attributes.attributesDescription.replacingOccurrences(of: "<div>", with: "")
                                .replacingOccurrences(of: "</div>", with: "")
                                .replacingOccurrences(of: "&nbsp;", with: "")
                                .replacingOccurrences(of: "<br>", with: "")
                                .replacingOccurrences(of: "&amp;", with: "")
                                .replacingOccurrences(of: "<em>", with: "")
                                .replacingOccurrences(of: "</em>", with: "")
                                .replacingOccurrences(of: "<strong>", with: "")
                                .replacingOccurrences(of: "</strong>", with: "")
                            ?? "", lineLimit: 4
                        ).padding(.bottom, 5)
                            .foregroundColor(.gray)
                        
                        
                        HStack {
                            Image(systemName: "play.circle")
                            Text(episode?.attributes.durationInMmss ?? "")
                        }
                    }
                    Spacer()
                }.padding(.vertical, paddingVer)
                    .padding(.horizontal, paddingHor)
                    .onTapGesture {
                        streamer.switchToEpisode(episode: episode, show: showTitle ?? "")
                        let haptic = UINotificationFeedbackGenerator()
                        haptic.notificationOccurred(.success)
                    }
            } else {
                if let business = business {
                    
                    HStack {
                        VStack(alignment: .leading, spacing: 0){
                            HStack {
                                HStack {
                                    Circle()
                                        .frame(width: 10, height: 10)
                                        .foregroundColor(Color(hex: 0xffafcc))
                                    
                                    Text(business.category)
                                        .font(.caption)
                                        .fontWeight(.light)
                                        .foregroundColor(.gray)
                                }
                                .padding(.horizontal, 15)
                                .padding(.vertical, 10)
                                .overlay(
                                    Capsule()
                                        .stroke(Color.white, lineWidth: 1.5)
                                )
                                Spacer()
                                /* ADD: Actions */
                                HStack {
                                    if !business.address.isEmpty {
                                        Button { actionSheet(address: business.address) } label: {
                                            Image(systemName: "map")
                                                .imageScale(.large)                                            .padding(8)
                                        }
                                    }
                                    if !business.contact.isEmpty {
                                        if ((business.contact.first?.isLetter) == true) {
                                            
                                            Button {
                                                let mailSubject = "?subject=WPRK Membership Deal".replacingOccurrences(of: " ", with: "%20")
                                                guard let url =  URL(string: "mailto:\(business.contact)" + mailSubject) else { return }
                                                UIApplication.shared.open(url)
                                            } label: {
                                                Image(systemName: "envelope")
                                                    .imageScale(.large)                                            .padding(8)
                                            }
                                            
                                        } else {
                                            Button {
                                                let telephone = "tel://"
                                                let formattedString = telephone + business.contact
                                                guard let url = URL(string: formattedString) else { return }
                                                UIApplication.shared.open(url)
                                                
                                            } label: {
                                                
                                                
                                                Image(systemName: "phone")
                                                    .imageScale(.large)
                                                    .padding(8)
                                                
                                            }
                                        }
                                    }
                                }.font(.subheadline)
                                    .foregroundColor(.gray)
                                
                                
                                
                            }
                            .padding(.leading, 1)
                            .padding(.bottom)
                            Text(business.title )
                                .foregroundColor(.white)
                                .bold()
                                .font(.headline)
                            Text(business.description)
                                .lineLimit(nil)
                                .fixedSize(horizontal: false, vertical: true)
                                .frame(maxHeight: .infinity)
                                .foregroundColor(.gray)
                            
                            
                        }
                        Spacer()
                    }.padding(.vertical)
                }
            }
        }
    }
    func actionSheet(address: String) {
        let av = UIActivityViewController(activityItems: [address], applicationActivities: nil)
        UIApplication.shared.windows.first?.rootViewController?.present(av, animated: true, completion: nil)
    }
}


