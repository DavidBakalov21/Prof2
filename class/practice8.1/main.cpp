#include <print>
#include <concepts>
template <typename T>
concept Product = requires(T obj) {
    { obj.getCoefficient() } -> std::convertible_to<double>;
    { obj.getBasePrice() } -> std::convertible_to<double>;
};

template <size_t PRIORITY>
class pricePrinter {
public:
    template <Product T1, Product T2>
    void printTotalPrice(const T1& obj1, const T2& obj2) const {
        double totalPrice = PRIORITY * obj1.getCoefficient() * obj1.getBasePrice() + obj2.getCoefficient() * obj2.getBasePrice();
        std::println("Total Price: {}", totalPrice);
    }
};

class Milk {
public:
    Milk(const double& coeff, const double& bPrice) : coefficient(coeff), basePrice(bPrice) {}

    double getCoefficient() const {
        return coefficient;
    }

    double getBasePrice() const {
        return basePrice;
    }
private:
    double coefficient;
    double basePrice;
};

class Cookies {

public:
    Cookies(const double& coeff, const double& bPrice) : coefficient(coeff), basePrice(bPrice) {}

    double getCoefficient() const {
        return coefficient;
    }

    double getBasePrice() const {
        return basePrice;
    }
private:
    double coefficient;
    double basePrice;
};

class Pineapple {
public:
    Pineapple(const double& coeff, const double& bPrice) : coefficient(coeff), basePrice(bPrice) {}

    double getCoefficient() const {
        return coefficient;
    }

    double getBasePrice() const {
        return basePrice;
    }
private:
    double coefficient;
    double basePrice;
};

int main() {
    Milk milk(2.0,3.0);
    Cookies cookies(3.0,6.0);
    Pineapple pineapple(4.0,8.0);

    pricePrinter<2> presenter;
    presenter.printTotalPrice(milk, cookies);
    presenter.printTotalPrice(pineapple, cookies);
    return 0;
}
