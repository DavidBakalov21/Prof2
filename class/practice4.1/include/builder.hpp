#pragma once

#include <string>
#include <map>
class Pizza {
public:
    std::string first;
    std::string second;
    std::string third;
    std::string fourth;
    double price;

    Pizza();
    std::string showPizza() const;
};

class PizzaBuilder {
public:
    virtual void setFirst(const std::string& first, const double price) = 0;
    virtual void setSecond(const std::string& second, const double price) = 0;
    virtual void setThird(const std::string& third, const double price) = 0;
    virtual void setFourth(const std::string& fourth, const double price) = 0;
    virtual Pizza* getPizza() = 0;
};


class BestPizzaBuilder : public PizzaBuilder {
private:
    Pizza* pizza;

public:
    BestPizzaBuilder();
    void setFirst(const std::string& firstPicked, const double cost) override;
    void setSecond(const std::string& secondPicked, const double cost) override;
    void setThird(const std::string& thirdPicked, const double cost) override;
    void setFourth(const std::string& fourthPicked, const double cost) override;
    Pizza* getPizza() override;
};

class Director {
private:
    PizzaBuilder* builder;

public:
    void setBuilder(PizzaBuilder* b);
    void constructPizza(const std::string& first, const std::string& second, const std::string& third , const std::string& fourth, const double firstPrice, const double secondPrice, const double thirdPrice, const double addonPrice);
};