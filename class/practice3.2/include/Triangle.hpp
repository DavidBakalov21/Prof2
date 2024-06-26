#pragma once
#include <optional>
#include <string>
class Triangle {
public:

    Triangle(const double& first, const double& second, const double& third);

    [[nodiscard]] std::optional<double> calculateArea();

private:
    double a;
    double b;
    double c;

    bool isValid();

   
};