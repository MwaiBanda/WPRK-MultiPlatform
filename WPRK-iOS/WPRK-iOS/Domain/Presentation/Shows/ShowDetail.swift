//
//  ShowDetail.swift
//  WPRK (iOS)
//
//  Created by Mwai Banda on 11/1/21.
//

import SwiftUI
import WPRKSDK

struct ShowDetail: View {
    var show: Show
    var body: some View {
        ScrollView {
            VStack(spacing: 0) {
                HeroImageGradient(
                    imageUrl: show.image,
                    category: show.category == "unset" ? "WPRK" : show.category ?? "WPRK"
                )
                HStack {
                    VStack(alignment: .leading) {
                        
                        Text(show.title)
                            .font(.title)
                            .bold()
                            .padding(.vertical, 5)
                        ExpandableText(show.description_
                                        .replacingOccurrences(of: "<p>", with: "")
                                        .replacingOccurrences(of: "<p", with: "")
                                        .replacingOccurrences(of: "style=", with: "")
                                        .replacingOccurrences(of: "</strong", with: "")
                                        .replacingOccurrences(of: "\"margin-", with: "")
                                        .replacingOccurrences(of: "left:", with: "")
                                        .replacingOccurrences(of: "20px;", with: "")
                                        .replacingOccurrences(of: "</p>", with: "")
                                        .replacingOccurrences(of: "<a href=", with: "\n")
                                        .replacingOccurrences(of: "\"", with: "\n")
                                        .replacingOccurrences(of: "</a>", with: "")
                                        .replacingOccurrences(of: "&nbsp;", with: "")
                                        .replacingOccurrences(of: "<br>", with: "")
                                        .replacingOccurrences(of: "&amp;", with: "")
                                        .replacingOccurrences(of: "<em>", with: "")
                                        .replacingOccurrences(of: "</em>", with: "")
                                        .replacingOccurrences(of: "<strong>", with: "")
                                        .replacingOccurrences(of: "<span", with: "")
                                        .replacingOccurrences(of: "class=", with: "")
                                        .replacingOccurrences(of: "\"  \"", with: "")
                                        .replacingOccurrences(of: ">", with: "")
                                        .replacingOccurrences(of: "r-1qd0xha", with: "")
                                        .replacingOccurrences(of: "r-ad9z0x", with: "")
                                        .replacingOccurrences(of: "r-bcqeeo", with: "")
                                        .replacingOccurrences(of: "r-qvutc0", with: "")
                                        .replacingOccurrences(of: "css-901oao", with: "")
                                        .replacingOccurrences(of: "css-16my406", with: "")
                                        .replacingOccurrences(of: "</span", with: "")
                                        .replacingOccurrences(of: "</strong>", with: "")
                                        .trimmingCharacters(in: .whitespacesAndNewlines)
                                       , lineLimit: 4
                        )
                            .foregroundColor(.gray)
                            .lineLimit(4)
                        Divider().foregroundColor(.gray)
                        VStack(alignment: .leading, spacing: 0) {
                            Text("Schedule")
                                .font(.title)
                                .bold()
                            Text("Set Reminders To Get Notified When Show Starts")
                                .foregroundColor(.gray)
                        }
                        Divider().foregroundColor(.gray)
                            .padding(.vertical, 5)
                        HStack {
                            VStack(alignment: .leading) {
                                HStack {
                                    Image(systemName: "calendar.badge.plus")
                                    Text(show.getFormattedDate())
                                        .foregroundColor(.white)
                                }
                                HStack {
                                    Image(systemName: "clock.badge.exclamationmark.fill").foregroundColor(.gray)
                                    Text(show.getTime12(time: .START)) +
                                    Text(" - \(show.getTime12(time: .END))")
                                }.foregroundColor(.white)
                                    .padding(.vertical, 5)
                                
                            }.foregroundColor(.gray)
                            Spacer()
                            Menu {
                                
                                Button(action: {
                                    let dateAsString = "\(show.getDate()) \(show.getTime12(time: .START))"
                                    let dateFormatter = DateFormatter()
                                    dateFormatter.dateFormat = "yyyy-MM-dd h:mm a"
                                    
                                    let dueDate = dateFormatter.date(from: dateAsString)
                                    let content = UNMutableNotificationContent()
                                    content.title = "Reminder ⏰"
                                    
                                    let formatter = DateFormatter()
                                    formatter.dateStyle = .full
                                    formatter.timeStyle = .short
                                    content.body = "\(show.title) is now Live on WPRK 91.5FM"
                                    content.sound = UNNotificationSound.default
                                    
                                    let calendar = Calendar(identifier: .gregorian)
                                    let components = calendar.dateComponents([.month, .day, .hour, .minute], from: dueDate!)
                                    
                                    let trigger = UNCalendarNotificationTrigger(dateMatching: components, repeats: false)
                                    
                                    let request = UNNotificationRequest(identifier: show.title, content: content, trigger: trigger)
                                    
                                    let center = UNUserNotificationCenter.current()
                                    center.add(request)
                                    let haptic = UINotificationFeedbackGenerator()
                                    haptic.notificationOccurred(.success)
                                }) {
                                    Label("Set Reminder", systemImage: "deskclock")
                                    
                                }
                            } label: {
                                VStack(alignment: .trailing) {
                                    Image(systemName: "deskclock")
                                        .font(.title2)
                                        .foregroundColor(Color(.lightGray))
                                    
                                }
                                .padding(.trailing)
                                .offset(y: -4)
                            }
                        }
                        .padding(.bottom)
                        
                    }.padding(.leading)
                    Spacer()
                }
            }
        }
        
    }
}



