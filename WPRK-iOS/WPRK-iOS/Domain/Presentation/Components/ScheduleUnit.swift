//
//  ScheduleUnit.swift
//  WPRK-iOS
//
//  Created by Mwai Banda on 4/7/23.
//

import SwiftUI

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

