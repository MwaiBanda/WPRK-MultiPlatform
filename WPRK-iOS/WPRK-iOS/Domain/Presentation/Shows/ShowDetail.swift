//
//  ShowDetail.swift
//  WPRK (iOS)
//
//  Created by Mwai Banda on 11/1/21.
//

import SwiftUI

struct ShowDetail: View {
    var show: Show
    var body: some View {
        ScrollView {
            VStack(spacing: 0) {
                ImageCover(imageUrl: show.image, category: show.category == "unset" ? "WPRK" : show.category ?? "WPRK")
                HStack {
                    VStack(alignment: .leading) {
                        
                        Text(show.title)
                            .font(.title)
                            .bold()
                            .padding(.vertical, 5)
                        ExpandableText(show.description
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


struct ExpandableText: View {
    @State private var expanded: Bool = false
    @State private var truncated: Bool = false
    @State private var shrinkText: String
    private var text: String
    let font: UIFont
    let lineLimit: Int
    private var moreLessText: String {
        if !truncated {
            return ""
        } else {
            return self.expanded ? " read less" : " ... read more"
        }
    }
    
    init(_ text: String, lineLimit: Int, font: UIFont = UIFont.preferredFont(forTextStyle: UIFont.TextStyle.body)) {
        self.text = text
        self.lineLimit = lineLimit
        _shrinkText =  State(wrappedValue: text)
        self.font = font
    }
    
    var body: some View {
        
        
        ZStack(alignment: .bottomLeading) {
            Group {
                Text(self.expanded ? text : shrinkText)
                + Text(moreLessText)
                    .bold()
                    .foregroundColor(Color(hex: 0xffafcc))
                
            }.animation(.easeInOut)
                .lineLimit(expanded ? nil : lineLimit)
                .background(
                    // Render the limited text and measure its size
                    Text(text).lineLimit(lineLimit)
                        .background(GeometryReader { visibleTextGeometry in
                            Color.clear.onAppear() {
                                let size = CGSize(width: visibleTextGeometry.size.width, height: .greatestFiniteMagnitude)
                                let attributes:[NSAttributedString.Key:Any] = [NSAttributedString.Key.font: font]
                                ///Binary search until mid == low && mid == high
                                var low  = 0
                                var heigh = shrinkText.count
                                var mid = heigh ///start from top so that if text contain we does not need to loop
                                while ((heigh - low) > 1) {
                                    let attributedText = NSAttributedString(string: shrinkText + moreLessText, attributes: attributes)
                                    let boundingRect = attributedText.boundingRect(with: size, options: NSStringDrawingOptions.usesLineFragmentOrigin, context: nil)
                                    if boundingRect.size.height > visibleTextGeometry.size.height {
                                        truncated = true
                                        heigh = mid
                                        mid = (heigh + low)/2
                                        
                                    } else {
                                        if mid == text.count {
                                            break
                                        } else {
                                            low = mid
                                            mid = (low + heigh)/2
                                        }
                                    }
                                    shrinkText = String(text.prefix(mid))
                                }
                                if truncated {
                                    shrinkText = String(shrinkText.prefix(shrinkText.count - 2))  //-2 extra as highlighted text is bold
                                }
                            }
                        })
                        .hidden() // Hide the background
                )
                .font(Font(font)) ///set default font
            if truncated {
                Button(action: {
                    expanded.toggle()
                }, label: {
                    HStack { //taking tap on only last line, As it is not possible to get 'see more' location
                        Spacer()
                        Text("")
                    }.opacity(0)
                })
            }
        }
    }
    
}
