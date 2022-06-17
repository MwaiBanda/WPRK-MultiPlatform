//
//  ShowAPI.swift
//  WPRK
//
//  Created by Mwai Banda on 10/2/21.
//

import Foundation

final class ContentServiceImplementation: ContentService {
    
    func getShows(onCompletion: @escaping (Result<[Show], Error>) -> ()) {
        let url = URL(string: "https://spinitron.com/api/shows?access-token=\(Constants.SPINITRON_KEY)")!
        let request  = URLRequest(url: url)
        let task = URLSession.shared.dataTask(with: request) { (data, res, err) in
            if let error = err {
                onCompletion(.failure(error))
            } else {
                if let data = data, let response = res as? HTTPURLResponse {
                    if response.statusCode == 200 {
                        do {
                            let shows = try JSONDecoder().decode(Shows.self, from: data)
                            onCompletion(.success(shows.items))
                        } catch DecodingError.dataCorrupted(let context) {
                            print(context)
                        } catch DecodingError.keyNotFound(let key, let context) {
                            print("Key '\(key)' not found:", context.debugDescription)
                            print("codingPath:", context.codingPath)
                        } catch DecodingError.valueNotFound(let value, let context) {
                            print("Value '\(value)' not found:", context.debugDescription)
                            print("codingPath:", context.codingPath)
                        } catch DecodingError.typeMismatch(let type, let context) {
                            print("Type '\(type)' mismatch:", context.debugDescription)
                            print("codingPath:", context.codingPath)
                        } catch {
                            print("error: ", error.localizedDescription)
                        }
                    }
                }
            }
        }
        task.resume()
    }
    
    func getPodcasts(onCompletion: @escaping  (Result<[Podcast], Error>) -> ()){
        let url = URL(string:"https://api.transistor.fm/v1/shows")!
        var request = URLRequest(url: url)
        request.setValue(Constants.TRANSISTOR_KEY, forHTTPHeaderField: "x-api-key")
        let task = URLSession.shared.dataTask(with: request) { data, res, err in
            if let error = err {
                onCompletion(.failure(error))
            } else {
                if let data = data, let response = res as? HTTPURLResponse {
                    if response.statusCode == 200 {
                        do {
                            let podcasts = try JSONDecoder().decode(Podcasts.self, from: data)
                            onCompletion(.success(podcasts.data))
                        } catch DecodingError.dataCorrupted(let context) {
                            print(context)
                        } catch DecodingError.keyNotFound(let key, let context) {
                            print("Key '\(key)' not found:", context.debugDescription)
                            print("codingPath:", context.codingPath)
                        } catch DecodingError.valueNotFound(let value, let context) {
                            print("Value '\(value)' not found:", context.debugDescription)
                            print("codingPath:", context.codingPath)
                        } catch DecodingError.typeMismatch(let type, let context) {
                            print("Type '\(type)' mismatch:", context.debugDescription)
                            print("codingPath:", context.codingPath)
                        } catch {
                            print("error: ", error.localizedDescription)
                        }
                    }
                }
            }
        }
        task.resume()
    }
    
    func getEpisodes(showID: String, onCompletion: @escaping (Result<[Episode], Error>) -> ()) {
        let url = URL(string:"https://api.transistor.fm/v1/episodes?show_id=\(showID)")!
        var request = URLRequest(url: url)
        request.httpMethod = "GET"
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        request.addValue("application/json", forHTTPHeaderField: "Accept")
        request.setValue(Constants.TRANSISTOR_KEY, forHTTPHeaderField: "x-api-key")
        let task = URLSession.shared.dataTask(with: request) { data, res, err in
            if let error = err {
                onCompletion(.failure(error))
            } else {
                if let data = data, let response = res as? HTTPURLResponse {
                    if response.statusCode == 200 {
                        do {
                            let dataString = String(data: data, encoding: .utf8)
                            if let jsondata = dataString?.data(using: .utf8) {
                                let result = try JSONDecoder().decode(Episodes.self, from: jsondata)
                                onCompletion(.success(result.data))
                            }
                        } catch DecodingError.dataCorrupted(let context) {
                            print(context)
                        } catch DecodingError.keyNotFound(let key, let context) {
                            print("Key '\(key)' not found:", context.debugDescription)
                            print("codingPath:", context.codingPath)
                        } catch DecodingError.valueNotFound(let value, let context) {
                            print("Value '\(value)' not found:", context.debugDescription)
                            print("codingPath:", context.codingPath)
                        } catch DecodingError.typeMismatch(let type, let context) {
                            print("Type '\(type)' mismatch:", context.debugDescription)
                            print("codingPath:", context.codingPath)
                        } catch {
                            print("error: ", error.localizedDescription)
                        }
                    }
                }
            }
        }
        task.resume()
    }
    private init() { }
    static let sharedInstance = ContentServiceImplementation()
}
