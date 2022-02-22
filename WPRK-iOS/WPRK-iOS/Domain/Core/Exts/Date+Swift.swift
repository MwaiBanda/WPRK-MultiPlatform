//
//  Date+Swift.swift
//  WPRK (iOS)
//
//  Created by Mwai Banda on 9/13/21.
//

import Foundation

extension Date {
    func dayNumberOfWeek() -> Int? {
        return Calendar.current.dateComponents([.weekday], from: self).weekday
    }
}

//
