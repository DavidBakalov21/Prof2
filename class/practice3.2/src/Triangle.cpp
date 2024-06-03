#include "Triangle.hpp"
#include <cmath>
#include <string>
#include <format>

Triangle::Triangle(const double& first, const double& second, const double& third): a(first), b(second), c(third){}

std::string Triangle::getArea(){
    if (isValid()){
        return std::format("{:.2f}", calculateArea());
    }else{
        return "tringle is invalid";
    }
};

double Triangle::calculateArea(){
    double s = (a + b + c) / 2.0;
    double area = sqrt(s * (s - a) * (s - b) * (s - c));
    return area;
}

bool Triangle::isValid(){
    return (a>0 && b>0 && c>0) && (a + b > c && a + c > b && b + c > a);
}