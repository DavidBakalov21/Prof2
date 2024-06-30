#include <iostream>
#include <vector>
#include <print>
#include <Maybe.hpp>

int main() {
    RectCollection collection;

    for (int i = 1; i <= 5; i++) {
        double width = 0.0, height = 0.0;
        std::println("Enter rectangle {} :", i);
        std::cin >> width >> height;
        collection.addRectangle(Rect(width, height));
    }

    collection.printFitInformation();

    std::println("The biggest area: {}",collection.getBiggestAreaRectangle().getArea());
    std::println("Number of recangles: {}",collection.getRectNum());
    std::println("The smallest area: {}",collection.getSmallestAreaRectangle().getArea());

    collection.biggestSidePrint();

    std::println("Total area of rectangles: {}",collection.getTotalArea());

    return 0;
}
