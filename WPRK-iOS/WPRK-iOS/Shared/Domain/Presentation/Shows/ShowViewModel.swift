//
//  LiveViewModel.swift
//  WPRK (iOS)
//
//  Created by Mwai Banda on 10/2/21.
//

import Foundation

class ShowViewModel: ObservableObject {
    
    func getCurrent() -> String {
        return Date().string(format: "yyyy-MM-dd")
    }
    func getDayByOffset(offset: Int) -> String {
        var dayComponent    = DateComponents()
        dayComponent.day    = offset
        let theCalendar     = Calendar.current
        let nextDate = theCalendar.date(byAdding: dayComponent, to: Date())
        return nextDate?.string(format: "yyyy-MM-dd") ?? ""
    }
   
}

extension Date {
    func string(format: String) -> String {
        let formatter = DateFormatter()
        formatter.dateFormat = format
        return formatter.string(from: self)
    }
    
  
}
