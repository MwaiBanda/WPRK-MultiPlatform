//
//  MembershipHome.swift
//  WPRK (iOS)
//
//  Created by Mwai Banda on 9/10/21.
//

import SwiftUI
import AVKit

struct MembershipHome: View {
    @ObservedObject var streamer: WPRKStreamer
    enum AccountLayout {
        case account
        case card
        case deals
    }
    let buttonName = [
        "Account", "Card", "Deals"
    ]
    let deals = [
        BusinessDeal(category: "DRINKS", title: "Nora's Sugar Shack", description: "$1 off bottles of wine and 6 packs, free gift while supply lasts over $10", contact: "(407)447-5885", address: "636 Virginia Drive, Orlando, FL 32803"),
        BusinessDeal(category: "FOOD", title: "Tako Cheena", description: "15% off", contact: "(407)690-6270", address: "938 N Mills Ave, Orlando, FL 32803"),
        BusinessDeal(category: "FOOD", title: "Pop's Pizzeria", description: "15% off", contact: "(407)690-6270", address: "932 N Mills Ave, Orlando, FL 32803"),
        BusinessDeal(category: "CLOTHING", title: "The Owl's Attic", description: "10% off all vintage sales", contact: "(321)300-6957", address: "3106 Corrine Dr, Orlando, FL 32803"),
        BusinessDeal(category: "FOOD", title: "Bolay", description: "10% off order", contact: "(407)907-6577", address: "1971 Aloma Ave, Winter Park, FL 32792"),
        BusinessDeal(category: "MEDIA", title: "Annie Russell Theatre", description: "BOGO any Wednesday or Thursday show", contact: "(407)389-1400", address: "1000 Holt Ave, Winter Park, FL 32789"),
        BusinessDeal(category: "FOOD", title: "Antonella's Pizzeria", description: "20% off", contact: "(407)636-5333", address: "360 W Fairbanks Ave, Winter Park, FL 32789"),
        BusinessDeal(category: "FOOD", title: "Something Fishy", description: "Taco Wednesday for members and all Rollins students with id", contact: "(407)951-8686", address: "249 W SR 436, Altamonte Springs FL 32714"),
        BusinessDeal(category: "SERVICE", title: "Window World Orlando", description: "$250 off of 4 or more", contact: "(407)383-9693", address: "3882 Center Loop, Orlando, FL 32808"),
        BusinessDeal(category: "EDUCATION", title: "The Princeton Review", description: "20% off select online courses *with coupon code", contact: "", address: ""),
        BusinessDeal(category: "PHOTOGRAPHY", title: "Kate Taramykin Studios", description: "10% off any session", contact: "hello@katetaramykin.com", address: ""),
        BusinessDeal(category: "ART", title: "Gabby Shepherd Artist", description: "WPRK 1 take 20% off select items", contact: "gabbyshepherdartist@gmail.com", address: ""),
        BusinessDeal(category: "MEDIA", title: "Aloma Cinema Grill", description: "1 free movie pass each month per member", contact: "", address: ""),
        BusinessDeal(category: "CAFÉ", title: "Blackbird Comics & Coffeehouse", description: "10% off your purchase (cannot be combined with other discounts)", contact: "(321)316-4296", address: "500 E Horatio Ave, Ste 3, Maitland, FL 32751"),
        BusinessDeal(category: "FOOD", title: "Karina's Confectioneries", description: "$5 off any purchase of $75 or more and $10 off $150 or more for current WRK members (cannot be combined with other discounts)", contact: "hello@karinasconfectioneries.com", address: "3201 Corrine Dr, Orlando, FL 32803"),
        BusinessDeal(category: "CAFÉ", title: "KOS Coffee & Bodega", description: "Free Coffee (Drip, Espresso Based Drink, or Cold Brew)", contact: "info@choosekos.com", address: "129 W Fairbanks Ave, Winter Park, FL 32789"),

    ]
    @State private var selected = "Account"
    var body: some View {
        ScrollView(showsIndicators: false) {
            VStack{
             
        VStack(alignment: .leading) {
            HStack {
                Text("Membership")
                    .font(.title)
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
                    .font(.title)
                    .fontWeight(.heavy)
                Spacer()
            
                
            }
            Text("Discover Deals & Discounts Available Locally")
                .foregroundColor(.gray)
                }
            Divider().background(Color(.lightGray))
                ScrollView(showsIndicators: false) {
                    
                
            ForEach(deals, id: \.id) { deal in
                ContentRow(business: deal)
                Divider().background(Color(.lightGray))

            }
                }.frame(height: DeviceType.deviceIsPad ? .infinity : (DeviceType.iPhone678  ||  DeviceType.iPhone12 || DeviceType.iPhoneX ? screenBounds.height - screenBounds.height * 0.43  : DeviceType.iPhone678p ? screenBounds.height - screenBounds.height * 0.4 : screenBounds.height - screenBounds.height * 0.385))
            Spacer()
        }
        }
        .padding(.top, 5)
        .foregroundColor(.white)
        .background(Color.white.opacity(0).ignoresSafeArea(.all))
        .onAppear {
            AppReviewRequest.RequestReviewWhenNeeeded()
        }
    }
}


struct Account_Previews: PreviewProvider {
    static var previews: some View {
        MembershipHome(streamer: WPRKStreamer.sharedInstance)
    }
}
