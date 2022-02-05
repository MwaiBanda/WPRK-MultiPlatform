//
//  Shows.swift
//  WPRK
//
//  Created by Mwai Banda on 10/2/21.
//

import Foundation


struct Shows: Decodable {
    var items: [Show]
    var links: WelcomeLinks
    var meta: ShowsMetaData

       enum CodingKeys: String, CodingKey {
           case items
           case links = "_links"
           case meta = "_meta"
       }
}

// MARK: - Item
struct Show: Decodable, Identifiable {

    var id: Int
    var start, end: String
    var duration: Int
    var timezone: String
    var oneOff: Bool
    var category: Category?
    var title, itemDescription: String
    var since: Int?
    var url: String
    var hideDj: Int
    var image: String
    var links: ItemLinks

    enum CodingKeys: String, CodingKey {
        case id, start, end, duration, timezone
        case oneOff = "one_off"
        case category, title
        case itemDescription = "description"
        case since, url
        case hideDj = "hide_dj"
        case image
        case links = "_links"
    }
}


extension Show {
    enum ShowTime {
        case START
        case END
    }
    func getTime24(time: ShowTime) -> String {
        switch time {
        case .START:
            return String(start.dropFirst(11).dropLast(8))
        case .END:
           return String(end.dropFirst(11).dropLast(8))
        }
    }
    func getTime12(time: ShowTime) -> String {
        let dateFormatter = DateFormatter()
        
        switch time {
        case .START:
            let dateAsString = getTime24(time: .START)
    
            dateFormatter.dateFormat = "HH:mm"

            let date = dateFormatter.date(from: dateAsString)
            dateFormatter.dateFormat = "h:mm a"
            let Date12Str = dateFormatter.string(from: date!)
            print("12 hour formatted Date:",Date12Str)
            let Date12 = dateFormatter.date(from: Date12Str)
            let calendar = NSCalendar.autoupdatingCurrent
            let newDateMinusFive = calendar.date(byAdding: .hour, value: -5, to: Date12!)
            let newDateStr = dateFormatter.string(from: newDateMinusFive!)
            return newDateStr
        case .END:
            let dateAsString = getTime24(time: .END)
            dateFormatter.dateFormat = "HH:mm"

            let date = dateFormatter.date(from: dateAsString)
            dateFormatter.dateFormat = "h:mm a"
            let Date12Str = dateFormatter.string(from: date!)
            print("12 hour formatted Date:",Date12Str)
            let Date12 = dateFormatter.date(from: Date12Str)
            let calendar = NSCalendar.autoupdatingCurrent
            let newDateMinusFive = calendar.date(byAdding: .hour, value: -5, to: Date12!)
            let newDateStr = dateFormatter.string(from: newDateMinusFive!)
            return newDateStr
        }
    }
    func getDate() -> String {
        let currentTime = getTime12(time: .START)
        let currentHour: Int = Int(String(currentTime.split(separator: ":").first ?? "0")) ?? 0
        if (currentTime.suffix(2) == "PM"){
             if currentHour > 6 && currentHour < 12 {
                let dateFormatter = DateFormatter()
                let timeAsString = getTime24(time: .END)
                
                let dateAsString = String(start.dropLast(14))
                dateFormatter.dateFormat = "yyyy-MM-dd HH:mm"
                
                let date = dateFormatter.date(from: dateAsString + " " + timeAsString)
                dateFormatter.dateFormat = "yyyy-MM-dd"
                let Date12Str = dateFormatter.string(from: date!)
                print("12 hour formatted Date:",Date12Str)
                let Date12 = dateFormatter.date(from: Date12Str)
                let calendar = NSCalendar.autoupdatingCurrent
                let newDateMinusFive = calendar.date(byAdding: .day, value: -1, to: Date12!)
                let newDateStr = dateFormatter.string(from: newDateMinusFive!)
                return newDateStr
            }
        }
        return String(start.dropLast(14))
    }
    func getFormattedDate() -> String {
        let dateAsString = getDate()
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd"
        let date = dateFormatter.date(from: dateAsString)
        dateFormatter.dateFormat = "E, d MMM yyyy"
        return dateFormatter.string(from: date!)

    }
    
    
}
enum Category: String, Codable {
    case music = "Music"
    case talk = "Talk"
    case sport = "Sport"
    case unset = "unset"
    case news = "News"
}


// MARK: - ItemLinks
struct ItemLinks: Codable, Hashable {

    var linksSelf: Last
    var personas: [Last]
    var playlists: Last

    enum CodingKeys: String, CodingKey {
        case linksSelf = "self"
        case personas, playlists
    }
}

// MARK: - Last
struct Last: Codable, Hashable {
    var href: String
}


// MARK: - WelcomeLinks
struct WelcomeLinks: Codable {
    let linksSelf, next, last: Last

    enum CodingKeys: String, CodingKey {
        case linksSelf = "self"
        case next, last
    }
}

// MARK: - Meta
struct ShowsMetaData: Codable {
    let totalCount, pageCount, currentPage, perPage: Int
}
