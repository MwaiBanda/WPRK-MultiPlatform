//
//  ShowAPI.swift
//  WPRK
//
//  Created by Mwai Banda on 10/2/21.
//

import Foundation

final class ContentServiceImplementation: ContentService {
     func getShows(completion: @escaping (Result<[Show], Error>) -> ()) {
        let url = URL(string: "https://spinitron.com/api/shows?access-token=\(Constants.SPINITRON_KEY)")!
        let request  = URLRequest(url: url)
        let task = URLSession.shared.dataTask(with: request) { (data, res, err) in
            DispatchQueue.main.async {
                do {
                    let shows = try JSONDecoder().decode(Shows.self, from: data!)
                    completion(.success(shows.items))
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
                    print("error: ", error)
                }
            }
        }
        task.resume()
    }
    
    func getPodcasts(completion: @escaping  (Result<[Podcast], Error>) -> ()){
        let url = URL(string:"https://api.transistor.fm/v1/shows")!
        var request = URLRequest(url: url)
        request.setValue(Constants.TRANSISTOR_KEY, forHTTPHeaderField: "x-api-key")
        let task = URLSession.shared.dataTask(with: request) { data, res, err in
            do {
                let podcasts = try JSONDecoder().decode(Podcasts.self, from: data!)
                completion(.success(podcasts.data))
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
                print("error: ", error)
            }
        }
        task.resume()
    }
    
    func getEpisodes(showID: String, completion: @escaping (Result<[Episode], Error>) -> ()) {
        let url = URL(string:"https://api.transistor.fm/v1/episodes?show_id=\(showID)")!
        var request = URLRequest(url: url)
        request.httpMethod = "GET"
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        request.addValue("application/json", forHTTPHeaderField: "Accept")
        request.setValue(Constants.TRANSISTOR_KEY, forHTTPHeaderField: "x-api-key")
        let task = URLSession.shared.dataTask(with: request) { data, res, err in
            if let data = data {
                do {
                    let dataString = String(data: data, encoding: .utf8)
                    let jsondata = dataString?.data(using: .utf8)
                    let result = try JSONDecoder().decode(Episodes.self, from: jsondata!)
                    completion(.success(result.data))
                } catch let error {
                    print("Localized Error: \(error.localizedDescription)")
                    print("Error: \(error)")
                }
            }
        }
        task.resume()
    }
    private init() { }
    static let sharedInstance = ContentServiceImplementation()
}
