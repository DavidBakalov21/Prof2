#pragma once
#include <string>
class Triangle {
public:

    Triangle(const double& first, const double& second, const double& third);

    double calculateArea();

private:
    double a;
    double b;
    double c;

    bool isValid();

   
};