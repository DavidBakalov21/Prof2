#include <builder.hpp>
#include <map>
#include <iostream>
#include <string>
#include <print>
#include <fstream>
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

std::string inputer(const std::string& message, const std::map<std::string, double>& availableProducts){
    std::println("Pizza builder {}",message);
    std::string product;
    std::getline(std::cin, product);
    auto it = availableProducts.find(product);
    if (it != availableProducts.end()) {
        return product;
    }else{
        return "nothing";
    }
     
}

int main(){
    Director director;
    BestPizzaBuilder gamingBuilder;
    director.setBuilder(&gamingBuilder);

    std::map<std::string, double> productList=readProducts("ingradients.txt");
    
    for (const auto& product : productList) {
        std::println("{}-{}$",product.first,product.second);
    }
    productList["nothing"]=0;
    std::string souce=inputer("What first do you want", productList);
    std::string meat=inputer("What second do you want", productList);
    std::string cheese=inputer("What third do you want", productList);
    std::string addons=inputer("What fourth do you want", productList);

    std::println("enter <done> to finish order");
    std::string choice;
    std::getline(std::cin, choice);
    
    if (choice=="done"){
        director.constructPizza(souce, meat, cheese, addons, productList[souce], productList[meat], productList[cheese], productList[addons]);
        Pizza* gamingPizza = gamingBuilder.getPizza();    
        std::println("Your pizza is {}",gamingPizza->showPizza());
        return 0;
    }else{
        std::println("exiting");
        return 0;
    }

    
}