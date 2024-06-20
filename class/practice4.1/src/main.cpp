#include <builder.hpp>
#include <map>
#include <iostream>
#include <string>
#include <print>
#include <fstream>
#include <memory>
std::map<std::string, double> readProducts(const std::string& filename){
    std::map<std::string, double> products;
    std::ifstream file(filename);
    std::string line;

    while (getline(file, line)) {
        std::size_t dashPos = line.find('-');
        if (dashPos != std::string::npos) {
            std::string name = line.substr(0, dashPos);
            double price = std::stod(line.substr(dashPos + 1));
            products[name] = price;
        }
    }

    file.close();
    return products;
}

std::optional<std::string> ingredientInputer(const std::string& message, const std::map<std::string, double>& availableProducts){
    std::println("Pizza builder {}",message);
    std::string product;
    std::getline(std::cin, product);
    auto it = availableProducts.find(product);

    if (it != availableProducts.end()) {
        return product;
    } else {
        return std::nullopt;
    }

}

int main(){
    Director director;
    BestPizzaBuilder gamingBuilder;
    director.setBuilder(&gamingBuilder);
    std::map<std::string, double> productList = readProducts("ingradients.txt");

    for (const auto& product : productList) {
        std::println("{}-{}$",product.first,product.second);
    }

    productList["nothing"] = 0;
    std::optional<std::string> ingredient1 = ingredientInputer("What first do you want", productList);
    std::optional<std::string> ingredient2 = ingredientInputer("What second do you want", productList);
    std::optional<std::string> ingredient3 = ingredientInputer("What third do you want", productList);
    std::optional<std::string> ingredient4 = ingredientInputer("What fourth do you want", productList);

    if (!ingredient1) ingredient1 = "nothing";
    if (!ingredient2) ingredient2 = "nothing";
    if (!ingredient3) ingredient3 = "nothing";
    if (!ingredient4) ingredient4 = "nothing";


    std::println("enter <done> to finish order");
    std::string choice;
    std::getline(std::cin, choice);

    if (choice == "done") {
        director.constructPizza(*ingredient1, *ingredient2, *ingredient3, *ingredient4, productList[*ingredient1], productList[*ingredient2], productList[*ingredient3], productList[*ingredient4]);
        std::unique_ptr<Pizza> gamingPizza = gamingBuilder.getPizza();
        std::println("Your pizza is {}",gamingPizza->showPizza());
        return 0;
    } else {
        std::println("exiting");
        return 0;
    }

    
}