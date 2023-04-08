//
//  LiveViewModel.swift
//  WPRK (iOS)
//
//  Created by Mwai Banda on 10/2/21.
//

import Foundation
import WPRKSDK

@MainActor
final class ShowViewModel: ObservableObject {
    @Published var shows = [Show]()
    @Published var scheduledShows = [Show]()
    @Published var currentDate = ""
    @Published var currentDay = ""
    @Published var selected: Show? = nil

    private func getShows() async {
        do {
            try await WPRK.shared.getShowUseCase.invoke { res in
                if let shows = res.data {
                    self.shows = shows as? [Show] ?? []
                    self.scheduledShows = (shows as? [Show] ?? []).filter({ $0.getDate() == self.currentDate})
                    print(shows)
                }
            }
        } catch {
            print(error.localizedDescription)
        }
    }
    
    func getShows() {
        Task { @MainActor in
            await self.getShows()
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

