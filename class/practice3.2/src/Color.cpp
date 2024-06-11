#include "Color.hpp"
#include <string>
#include <format>

Color::Color(const int R, const int G, const int B): red(R), green(G), blue(B) {}

Color Color::createMagicColor() const {
    if (isValid()) {
        int magicRed = (red / 2) - 1;
        int magicGreen = (green * 2) - 2;
        Color magicColor(magicRed, magicGreen, blue);
        if (!magicColor.isValid()) {
            return Color(0, 0, 0);
        }
        return magicColor;
    }
    return Color(0, 0, 0);
}

std::string Color::toString() const {
    return std::format("{},{},{}", red, green, blue);
}

bool Color::isValid() const {
    return (red >= 0 && red <= 255) &&
           (green >= 0 && green <= 255) &&
           (blue >= 0 && blue <= 255);
}

