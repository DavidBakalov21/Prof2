#pragma once
#include <string>

class Color {
public:
    Color(const int R, const int G, const int B);

    Color createMagicColor() const;

    std::string getColor() const;

private:
    bool isValid() const;

    int red;
    int green;
    int blue;
};