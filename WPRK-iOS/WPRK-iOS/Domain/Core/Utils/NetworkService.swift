//
//  StoreNetworkService.swift
//  Virtual Groceries
//
//  Created by Mwai Banda on 8/21/21.
//

import Foundation
import Network

final class NetworkService: ObservableObject {
    let queue = DispatchQueue(label: "VGStoreNetworkMonitorQueue")
    @Published var hasInternetAccess: Bool = false
    @Published var monitor = NWPathMonitor()
    static let sharedInstance = NetworkService()
    init(){
        monitor.pathUpdateHandler = { [weak self] path in
            DispatchQueue.main.async {
                self?.hasInternetAccess = path.status == .satisfied ? true : false
            }
        }
        monitor.start(queue: queue)
    }
}
