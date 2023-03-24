import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        TestKt.testAll()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
