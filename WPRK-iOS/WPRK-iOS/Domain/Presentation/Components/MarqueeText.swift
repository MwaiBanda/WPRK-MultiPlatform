import SwiftUI
import UIKit

struct MarqueeText: View {
    
    @State private var leftMost = false
    @State private var w: CGFloat = 0
    @State private var previousText: String = ""
    @State private var contentViewWidth: CGFloat = 0
    @State private var animationDuration: Double = 4.5
    @Binding var text : String
    
    var body: some View {
        let baseAnimation = Animation.linear(duration: self.animationDuration)
        let repeated = baseAnimation.repeatForever(autoreverses: false)
        return VStack(alignment:.center, spacing: 0) {
            GeometryReader { geometry in
                Text(self.text)
                    .font(.system(size: 24))
                    .lineLimit(1)
                    .foregroundColor(.clear)
                    .fixedSize(horizontal: true, vertical: false)
                    .background(TextGeometry())
                    .onPreferenceChange(WidthPreferenceKey.self, perform: {
                    self.w = $0
                    print("textWidth:\(self.w)")
                    print("geometry:\(geometry.size.width)")
                    self.contentViewWidth = geometry.size.width
                    if self.text.count != self.previousText.count && self.contentViewWidth < self.w {
                        let duration = self.w/50
                        print("duration:\(duration)")
                        self.animationDuration = Double(duration)
                        self.leftMost = true
                    } else {
                        self.animationDuration = 0.0
                    }
                    self.previousText = self.text
                    })
                    .fixedSize(horizontal: false, vertical: true)// This Text is temp, will not be displayed in UI. Used to identify the width of the text.
                if self.animationDuration > 0.0 {
                    Text(self.text)
                        .lineLimit(nil)
                        .foregroundColor(.white)
                        .fixedSize(horizontal: true, vertical: false)
                        .background(TextGeometry())
                        .onPreferenceChange(WidthPreferenceKey.self, perform: { _ in
                        if self.text.count != self.previousText.count && self.contentViewWidth < self.w {
                        } else {
                            self.leftMost = false
                        }
                        self.previousText = self.text
                        })
                        .modifier(self.makeSlidingEffect().ignoredByLayout())
                        .animation(repeated, value: self.leftMost)
                        .clipped(antialiased: true)
                        .offset(y: -8)//Text with animation'
                }
                else {
                    HStack {
                        Text(self.text).lineLimit(1).foregroundColor(.white).offset(y: -8)//Text without animation
                        Spacer()
                    }
                }
            }
        }.fixedSize(horizontal: false, vertical: true).layoutPriority(1).frame(maxHeight: 50, alignment: .center).clipped()
        
    }
    
    
    func makeSlidingEffect() -> some GeometryEffect {
        return SlidingEffect(
            xPosition: self.leftMost ? -self.w : self.w,
            yPosition: 0).ignoredByLayout()
    }
}

struct MarqueeText_Previews: PreviewProvider {
    @State static var myCoolText = "myCoolText"
    static var previews: some View {
        MarqueeText(text: $myCoolText)
    }
}

struct SlidingEffect: GeometryEffect {
    var xPosition: CGFloat = 0
    var yPosition: CGFloat = 0
    
    var animatableData: CGFloat {
        get { return xPosition }
        set { xPosition = newValue }
    }
    
    func effectValue(size: CGSize) -> ProjectionTransform {
        let pt = CGPoint(
            x: xPosition,
            y: yPosition)
        return ProjectionTransform(CGAffineTransform(translationX: pt.x, y: pt.y)).inverted()
    }
}

struct TextGeometry: View {
    var body: some View {
        GeometryReader { geometry in
            return Rectangle().fill(Color.clear).preference(key: WidthPreferenceKey.self, value: geometry.size.width)
        }
    }
}

struct WidthPreferenceKey: PreferenceKey {
    static var defaultValue = CGFloat(0)
    
    static func reduce(value: inout CGFloat, nextValue: () -> CGFloat) {
        value = nextValue()
    }
    
    typealias Value = CGFloat
}

struct MagicStuff: ViewModifier {
    func body(content: Content) -> some View {
        Group {
            content.alignmentGuide(.underlineLeading) { d in
                return d[.leading]
            }
        }
    }
}

extension HorizontalAlignment {
    private enum UnderlineLeading: AlignmentID {
        static func defaultValue(in d: ViewDimensions) -> CGFloat {
            return d[.leading]
        }
    }
    static let underlineLeading = HorizontalAlignment(UnderlineLeading.self)
}
