//
//  MembershipHome.swift
//  WPRK (iOS)
//
//  Created by Mwai Banda on 9/10/21.
//

import SwiftUI
import AVKit

struct MembershipHome: View {
    @ObservedObject var streamer: RadioStreamer
    enum AccountLayout {
        case account
        case card
        case deals
    }
    let buttonName = [
        "Account", "Card", "Deals"
    ]
    let deals = [
        BusinessDeals(category: "DRINKS", title: "Nora's Sugar Shack", description: "$1 off bottles of wine and 6 packs, free gift while supply lasts over $10"),
        BusinessDeals(category: "FOOD", title: "Tako Cheena", description: "15% off"),
        BusinessDeals(category: "FOOD", title: "Pop's Pizzeria", description: "15% off"),
        BusinessDeals(category: "CLOTHING", title: "The Owl's Attic", description: "10% off all vintage sales"),
        BusinessDeals(category: "FOOD", title: "Bolay", description: "10% off order"),
        BusinessDeals(category: "MEDIA", title: "Annie Russell Theatre", description: "BOGO any Wednesday or Thursday show"),
        BusinessDeals(category: "FOOD", title: "Antonella's Pizzeria", description: "20% off"),
        BusinessDeals(category: "FOOD", title: "Something Fishy", description: "Taco Wednesday for members and all Rollins students with id"),
        BusinessDeals(category: "SERVICE", title: "Window World Orlando", description: "$250 off of 4 or more"),
        BusinessDeals(category: "EDUCATION", title: "The Princeton Review", description: "20% off select online courses *with coupon code"),
        BusinessDeals(category: "PHOTOGRAPHY", title: "Kate Taramykin Studios", description: "10% off any session"),
        BusinessDeals(category: "ART", title: "Gabby Shepherd Artist", description: "WPRK 1 take 201% off select items"),
        BusinessDeals(category: "MEDIA", title: "Aloma Cinema Grill", description: "1 free movie pass each month per member"),
        BusinessDeals(category: "CAFÉ", title: "BAMF Comics & Coffee", description: "10% off your purchase (cannot be combined with other discounts)"),
        BusinessDeals(category: "FOOD", title: "Karina's Confectioneries", description: "$5 off any purchase of $75 or more and $10 off $150 or more for current WRK members (cannot be combined with other discounts)"),

        BusinessDeals(category: "CAFÉ", title: "BAMF Comics & Coffee", description: "10% off your purchase (cannot be combined with other discounts)"),

        BusinessDeals(category: "CAFÉ", title: "KOS Coffee & Bodega", description: "Free Coffee (Drip, Espresso Based Drink, or Cold Brew)!"),

    ]
    @State private var selected = "Account"
    var body: some View {
        ScrollView(showsIndicators: false) {
            VStack{
                Divider().background(Color(.lightGray))
                    .offset(y: 10)
        VStack(alignment: .leading) {
            HStack {
                Text("Membership")
                    .font(.largeTitle)
                    .fontWeight(.heavy)
                    .offset(y: -10)

                Spacer()
                LiveButton(streamer: streamer)

            }
            Text("Sign Up For WPRK")
                .offset(y: -30)
                .foregroundColor(.gray)
        }.offset(y: 10)
                Divider().background(Color(.lightGray))
                    .offset(y: -15)
            ZStack(alignment: .center) {
            HStack {
                Image("Card")
                    .resizable()
                    .cornerRadius(10)
                    .frame(maxWidth: DeviceType.deviceIsPad ?  450 : screenBounds.width - 30, minHeight: 220, maxHeight: 220, alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/)
                    .foregroundColor(.gray)
            }
                VStack {
                    Spacer()
                    HStack {
                        Button(action: { withAnimation(.spring()){
                            guard let url = URL(string: "https://securelb.imodules.com/s/1282/giving/index.aspx?sid=1282&gid=1&pgid=1418&cid=2828&dids=95") else { return }
                                UIApplication.shared.open(url)
                        }
                            let haptic = UIImpactFeedbackGenerator(style: .heavy)
                            haptic.impactOccurred()
                        } ){
                            HStack(alignment: .lastTextBaseline) {
                                Spacer()
                                Text("Don't have membership? ")
                                    .font(Font.subheadline.weight(.light)) +
                                    Text("Register here")
                                    .fontWeight(.bold)
                                    .foregroundColor(Color(hex: 0xffafcc))
                                Image(systemName: "hand.tap")
                                    .font(Font.subheadline.weight(.light))
                                Spacer()
                            }.font(.caption)
                            .foregroundColor(Color.white)
                            .padding()
                            

                            
                            
                        }
                     
                    }
                }
            }
                VStack(alignment: .leading) {
            HStack {
                Text("Featured Deals")
                    .font(.largeTitle)
                    .fontWeight(.heavy)
                Spacer()
            
                
            }
            Text("Discover Deals & Discounts Available Locally")
                .foregroundColor(.gray)
                }
            Divider().background(Color(.lightGray))
            ForEach(deals, id: \.id) { deal in
                ContentRow(category: deal.category, title: deal.title, description: deal.description)
                Divider().background(Color(.lightGray))

            }
            Spacer()
        }
        }
        .padding(.leading)
        .padding(.top, 5)
        .foregroundColor(.white)
        .background(Color.white.opacity(0).ignoresSafeArea(.all))
    }
}


struct Account_Previews: PreviewProvider {
    static var previews: some View {
        MembershipHome(streamer: RadioStreamer.sharedInstance)
    }
}
