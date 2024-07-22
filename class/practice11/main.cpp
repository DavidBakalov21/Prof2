#include <iostream>
#include <vector>
#include <string>
#include <random>
#include <print>
const std::vector<std::string> booksConstant={
        "The Topographer’s Clown.", "The Chamber of Beaver.", 
        "The Ironer of Kanban.", "The Piglet of Tire.", 
        "The Border of the Unix.", "The Half-Time Convince.", 
        "The Earthly Pillows.", "The Censorship of the Ping.", 
        "The True Powers.", "The Overturn of the Ling."
};


class Caffe{
    public:
        Caffe(const std::vector<std::string>& booksArgument):books(booksArgument), lastElement(booksArgument.size()){}
        std::string chooseBook(){
            if (books.empty()) {
                return "";
            }
            int chosenIndex = randomInt(firstElement, lastElement-1);
            std::string chosenBook = books[chosenIndex];
            removeFromList(chosenBook);
            lastElement=books.size();
            return chosenBook;
        }

    private:
        int randomInt(int min, int max) const {
            std::random_device rd;
            std::mt19937 gen(rd());
            std::uniform_int_distribution<> dis(min, max);
            return dis(gen);
        }

        void removeFromList(const std::string& book) {
            books.erase(std::remove(books.begin(), books.end(), book), books.end());
            
        }

        std::vector<std::string> books;
        int firstElement=0;
        int lastElement;
};
int main(){
    Caffe caffe(booksConstant);
    while (true){
        std::string chosenBook = caffe.chooseBook();
        if (chosenBook.empty()) {
            std::println("Adios amigo!");
            break;
        }
        std::println("How about: {} ?", chosenBook);
        std::println("Yes/No?");
        std::string response;
        std::cin >> response;
        if (response == "Yes") {
            std::println("Enjoy your book!");
            break;
        }
    }
}