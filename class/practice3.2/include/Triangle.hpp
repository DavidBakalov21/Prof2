#pragma once
#include <string>
class Triangle {
public:

    Triangle(const double& first, const double& second, const double& third);

    std::string getArea();

private:
    double a;
    double b;
    double c;

    bool isValid();

    double calculateArea();
};