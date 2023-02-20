//
//  Live.swift
//  WPRK (iOS)
//
//  Created by Mwai Banda on 9/9/21.
//

import SwiftUI
import SDWebImageSwiftUI
import AVKit

struct ShowHome: View {
    @ObservedObject var streamer: WPRKStreamer
    @State private var days = [
        "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    ]
    @StateObject var showViewModel = ShowViewModel()
    @State private var reader: ScrollViewProxy?
    
    
    var body: some View {
        ScrollView(showsIndicators: false) {
            LazyVStack(pinnedViews: [.sectionHeaders]) {
                Section {
                    VStack(alignment: .leading) {
                        HStack {
                            Text("Shows")
                                .font(.title)
                                .fontWeight(.heavy)
                                .offset(y: -10)
                            Spacer()
                            LiveButton(streamer: streamer)
                            
                            
                        }
                        Text("Tap Scheduled Shows")
                            .offset(y: -30)
                            .foregroundColor(.gray)
                    }.offset(y: 10)
                    Divider().background(Color(.lightGray))
                        .offset(y: -15)
                    
                    ScrollView(.horizontal, showsIndicators: false) {
                        HStack {
                            if showViewModel.shows.isEmpty {
                                ForEach(0..<10, id: \.self) { i in
                                    RoundedRectangle(cornerRadius: 10)
                                        .frame(width: 190, height: 200, alignment: .center)
                                        .foregroundColor(.gray).opacity(0.5)
                                        .redacted(reason: .placeholder)
                                }
                            } else {
                                ForEach(showViewModel.shows, id: \.id) { i in
                                    VStack {
                                        WebImage(url: URL(string: i.image))
                                            .resizable()
                                            .onTapGesture {
                                                showViewModel.selected = i
                                                let haptic = UIImpactFeedbackGenerator(style: .soft)
                                                haptic.impactOccurred()
                                            }
                                            .aspectRatio(contentMode: .fill)
                                            .frame(width: 190, height: 200)
                                            .cornerRadius(10)
                                            .foregroundColor(.gray)
                                    }
                                }
                            }
                        }
                    }.padding(.bottom, 5)

                    
                }
                
                
                Section {
                    ScrollView(showsIndicators: false) {
                        ScrollViewReader { value in
                            VStack {
                                if showViewModel.showsScheduled.isEmpty {
                                    ScheduleUnit(title: "Nothing Scheduled", category: "WPRK", startTime: "", endTime:"", currentDate: showViewModel.currentDate)
                                        .id(0)
                                        .padding(.top)
                                    Divider().background(Color(.lightGray))
                                } else {
                                    ForEach(Array(showViewModel.showsScheduled.enumerated()), id: \.element) { i, show in
                                        ScheduleUnit(title: show.title, category: show.category ?? "WPRK", startTime: show.getTime12(time: .START), endTime: show.getTime12(time: .END), currentDate: showViewModel.currentDate)
                                            .id(i)
                                            .padding(.top)
                                            .onTapGesture {
                                                showViewModel.selected = show
                                                let haptic = UIImpactFeedbackGenerator(style: .soft)
                                                haptic.impactOccurred()
                                            }
                                        if showViewModel.showsScheduled.last?.id != show.id  || showViewModel.showsScheduled.count < 3  {
                                            Divider().background(Color(.lightGray))
                                        }
                                    }
                                }
                            }
                        }
                    }
                    .frame(minHeight: 330)
                    
                } header: {
                    VStack {
                        VStack(alignment: .leading) {
                            HStack {
                                Text("Schedule")
                                    .font(.title)
                                    .fontWeight(.heavy)
                                Spacer()
                            }
                           
                            Text("Tap To See Shows Scheduled For The Day")
                                .foregroundColor(.gray)
                        }
                        VStack {
                            Divider().background(Color.gray)
                            ScrollViewReader { value in
                                
                                ScrollView(.horizontal, showsIndicators: false) {
                                    HStack {
                                        ForEach(days.indices, id: \.self) { i in
                                            VStack {
                                                ZStack {
                                                    Circle()
                                                        .frame(width: 90, height: 90, alignment: .center)
                                                        .cornerRadius(10)
                                                        .foregroundColor(showViewModel.currentDay == days[i] ? Color(.white).opacity(0.2) : Color(hex: 0xffafcc))
                                                        .overlay(
                                                            Circle()
                                                                .stroke(showViewModel.currentDay == days[i] ? Color(.gray) : Color.white, lineWidth: 1.5)
                                                        )
                                                        .padding(1)
                                                    Text(String(days[i].prefix(3)))
                                                        .bold()
                                                        .font(.title3)
                                                }
                                                .id(i)
                                                .onTapGesture {
                                                    if i == 0  {
                                                        showViewModel.currentDate = showViewModel.getCurrent()
                                                    } else {
                                                        showViewModel.currentDate = showViewModel.getDayByOffset(offset: i)
                                                    }
                                                    showViewModel.currentDay = days[i]
                                                    showViewModel.showsScheduled = showViewModel.shows.filter({ $0.getDate() == showViewModel.currentDate})
                                                    
                                                    let haptic =
                                                    UIImpactFeedbackGenerator(style: .soft)
                                                    haptic.impactOccurred()
                                                    withAnimation(.easeIn(duration: 0.28)) {
                                                        if i != days.indices.last {
                                                            value.scrollTo(i, anchor: .center)
                                                        } else {
                                                            value.scrollTo(i, anchor: .trailing)
                                                        }
                                                    }
                                                    
                                                }
                                                Text(days[i])
                                                    .bold()
                                                    .font(.title3)
                                            }.padding(.trailing)
                                        }
                                    }
                                }
                            }
                            Divider().background(Color(.lightGray))
                            
                            VStack(alignment: .leading, spacing: 0) {
                                HStack {
                                    Text("Scheduled")
                                        .font(.title)
                                        .fontWeight(.heavy)
                                    Spacer()
                                    Text(showViewModel.currentDay)
                                        .padding(.trailing)
                                        .foregroundColor(Color(.lightGray))
                                }
                                HStack {
                                    Text("Listings")
                                        .fontWeight(.heavy)
                                        .foregroundColor(.gray)
                                    Spacer()
                                    VStack(alignment: .trailing) {
                                        Text(showViewModel.currentDate)
                                    }
                                    .font(.caption)
                                    .padding(.trailing)
                                    .foregroundColor(Color(.lightGray))
                                }
                            }
                            .padding(.vertical)
                            Divider().background(Color(.lightGray))
                        }
                    }
                    .background(Color(.black))
                }
                
                Spacer()
            }
        }
        .redacted(reason: showViewModel.shows.isEmpty ? .placeholder : [])
        .padding(.top, 5)
        .foregroundColor(.white)
        .background(Color.white.opacity(0).ignoresSafeArea(.all))
        .onAppear {
            let currentDateStr = streamer.getCurrentDayOfWeek()
            while(currentDateStr != days.first) {
                days.append(days.remove(at: 0))
            }
            showViewModel.getShows()
            AppReviewRequest.RequestReviewWhenNeeeded()
            showViewModel.currentDate = showViewModel.getCurrent()
            showViewModel.currentDay = days.first ?? ""
        }
        .sheet(item: $showViewModel.selected){ show in
            ContentWrapper(streamer: streamer, navConfig: .detailConfig, navTitle: show.title) {
                ShowDetail(show: show)
            }
        }
    }
}
struct ScheduleUnit: View {
    var title: String
    var category: String
    var startTime: String
    var endTime: String
    var currentDate: String
    var body: some View {
        HStack(alignment: .lastTextBaseline) {
            VStack(alignment: .leading) {
                Text(title)
                    .fontWeight(.heavy)
                    .font(.title3)
                Text(category)
                    .foregroundColor(.gray)
                
            }
            Spacer()
            
            Menu {
                
                Button(action: {
                    let dateAsString = "\(currentDate) \(startTime)"
                    let dateFormatter = DateFormatter()
                    dateFormatter.dateFormat = "yyyy-MM-dd h:mm a"
                    
                    let dueDate = dateFormatter.date(from: dateAsString)
                    let content = UNMutableNotificationContent()
                    content.title = "Reminder ⏰"
                    
                    let formatter = DateFormatter()
                    formatter.dateStyle = .full
                    formatter.timeStyle = .short
                    content.body = "\(title) is now Live on WPRK 91.5FM"
                    content.sound = UNNotificationSound.default
                    
                    let calendar = Calendar(identifier: .gregorian)
                    let components = calendar.dateComponents([.month, .day, .hour, .minute], from: dueDate!)
                    
                    let trigger = UNCalendarNotificationTrigger(dateMatching: components, repeats: false)
                    
                    let request = UNNotificationRequest(identifier: title, content: content, trigger: trigger)
                    
                    let center = UNUserNotificationCenter.current()
                    center.add(request)
                    let haptic = UINotificationFeedbackGenerator()
                    haptic.notificationOccurred(.success)
                }) {
                    Label("Set Reminder", systemImage: "deskclock")
                    
                }
            } label: {
                VStack(alignment: .trailing, spacing: 0) {
                    Image(systemName: "deskclock")
                        .font(.title2)
                        .foregroundColor(Color(.lightGray))
                        .offset(y: 2)
                        .padding(.bottom, 6)
                        .opacity(startTime.isEmpty ? 0 : 1)
                    Text(startTime)
                        .fontWeight(.bold)
                }
                .padding(.trailing)
            }
        }
    }
}




