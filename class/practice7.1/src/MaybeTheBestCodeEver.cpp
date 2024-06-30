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

    for (int i = 0; i < collection.getRectNum(); i++) {
        for (int j = 0; j < collection.getRectNum(); j++) {
            if (i != j && collection.getCollection()[i].fitsInside(collection.getCollection()[j])) {
                std::println("Rectangle {} can be placed inside Rectangle {}", i + 1, j + 1);
            }
        }
    }

    std::println("The biggest area: {}",collection.getBiggestAreaRectangle()->getArea());
    std::println("Number of recangles: {}",collection.getRectNum());
    std::println("The smallest area: {}",collection.getSmallestAreaRectangle()->getArea());

    for (int i = 0; i < 5; i++) {
        std::println("The biggest side of rectangle {} : {}", i + 1,collection.getCollection()[i].getBiggestSide());
    }

    std::println("Total area of rectangles: {}",collection.getTotalArea());

    return 0;
}
