//
//  LiveViewModel.swift
//  WPRK (iOS)
//
//  Created by Mwai Banda on 10/2/21.
//

import Foundation

class ShowViewModel: ObservableObject {
    @Published var shows = [Show]()
    @Published var showsScheduled = [Show]()
    @Published var currentDate = ""
    @Published var currentDay = ""
    @Published var selected: Show? = nil
    var contentService: ContentService
    
    init(contentService: ContentService){
        self.contentService = contentService
    }
    
    func getShows() {
         contentService.getShows { result in
            switch(result) {
            case .success(let shows):
                self.shows = shows
                self.showsScheduled = shows.filter({ $0.getDate() == self.currentDate})
                print(shows)
                
            case .failure(let error):
                print(error.localizedDescription)
            }
        }
    }
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
