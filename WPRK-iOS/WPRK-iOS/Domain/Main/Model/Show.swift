//
//  Show.swift
//  WPRK-iOS
//
//  Created by Mwai Banda on 6/25/22.
//

import Foundation


struct Show: Identifiable {
    var id: Int
    var category: String?
    var description: String
    var duration: Int
    var end: String
    var image: String
    var since: Int?
    var start: String
    var timezone: String
    var title: String
    var url: String
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
