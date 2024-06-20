#include "builder.hpp"
#include <string>
#include <format>
Pizza::Pizza() :price(0.0) {}

std::string Pizza::showPizza() const {
    return std::format("First: {} Second: {} Third: {} Fourth: {} Price: ${}",
                       first, second, third, fourth, price);
}

BestPizzaBuilder::BestPizzaBuilder() {
    this->pizza = std::make_unique<Pizza>();
}

void BestPizzaBuilder::setFirst(const std::string& firstPicked, const double cost) {
    pizza->first = firstPicked;
    pizza->price += cost;
}

void BestPizzaBuilder::setSecond(const std::string& secondPicked, const double cost) {
    pizza->second = secondPicked;
    pizza->price += cost;
}

void BestPizzaBuilder::setThird(const std::string& thirdPicked, const double cost) {
    pizza->third = thirdPicked;
    pizza->price += cost;
}

void BestPizzaBuilder::setFourth(const std::string& fourthPicked, const double cost) {
    pizza->fourth = fourthPicked;
    pizza->price += cost;
}

std::unique_ptr<Pizza> BestPizzaBuilder::getPizza() {
    return std::move(pizza);
}

void Director::setBuilder(PizzaBuilder* b) {
    builder = b;
}

void Director::constructPizza(const std::string& first, const std::string& second, const std::string& third, const std::string& fourth, const double firstPrice, const double secondPrice, const double thirdPrice, const double fourthPrice) {
    builder->setFirst(first, firstPrice);
    builder->setSecond(second, secondPrice);
    builder->setThird(third, thirdPrice);
    builder->setFourth(fourth, fourthPrice);
}