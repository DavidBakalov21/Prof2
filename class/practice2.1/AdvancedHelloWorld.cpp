#include <string>
#include <vector>
#include <iostream>
#include <fstream>
#include <sstream>
#include <ranges>


namespace {
    const std::string bread = "bread";
    const std::string deleteCommand = "delete";
    const std::string database = "database.txt";
}
std::vector<std::string> splitString(const std::string& str, char delimiter) {
    auto view = str | std::ranges::views::split(delimiter) | std::ranges::views::transform([](auto&& subrange) {
        return std::string(subrange.begin(), subrange.end());
        });
    return std::vector<std::string>(view.begin(), view.end());
}

class Database {
private:
    int lineNumber = 0;
    bool found = false;
    std::string lineFound;
    std::vector<std::string> lines;
    void setVector() {
        std::ifstream file(database);
        std::string line;
        std::vector<std::string> linesSetter;
        while (std::getline(file, line)) {
            linesSetter.push_back(line);
        }
        file.close();
        lines = linesSetter;
    }
    void deleteUser(const std::string& WordToFind) {
        for (int i = 0; i < lines.size(); i++)
        {
            if (splitString(lines[i], ' ')[0] == WordToFind)
            {
                lines.erase(lines.begin() + i);
            }
        }
        std::ofstream fileEdit(database, std::ios::trunc);
        for (const auto& line : lines) {
            if (line != "")
            {
                fileEdit << line << std::endl;
            }
        }
        std::cout << "All data about this user was cleared" << std::endl;
    }
    void addNewUser(const std::string& WordToFind) {
        std::cout << "Welcome " << WordToFind << std::endl;
        std::ofstream fileEdit(database, std::ios::app);
        if (!fileEdit.is_open()) {
            std::cerr << "Could not open the file for writing!" << std::endl;

        }
        fileEdit << WordToFind << " 1" << std::endl;
        fileEdit.close();
    }
    void addToExistingUser(const std::string& WordToFind) {
        std::ofstream fileEdit(database, std::ios::trunc);
        std::vector<std::string> arraySplitted = splitString(lineFound, ' ');
        int num = std::stoi(arraySplitted[1]);
        num++;
        lines[lineNumber - 1] = arraySplitted[0] + " " + std::to_string(num);
        for (const auto& line : lines) {
            if (line != "")
            {
                fileEdit << line << std::endl;
            }
        }
        std::cout << "Hello again(x" << num << ")," << WordToFind << std::endl;
        fileEdit.close();
    }
public:
    void clearDatabase() {
        std::ofstream file(database, std::ios::trunc);
        file.close();
    }
    

    void findName(const std::string& WordToFind) {
        setVector();
        auto it = std::ranges::find_if(lines, [&](const std::string& line) {
            lineNumber++;
            if (!line.empty() && splitString(line, ' ')[0] == WordToFind) {
                return true;
            }
            return false;
            });
        if (it != lines.end()) {
            lineFound = *it;
            found=true;
        }
        else {
            found = false;
        }
    }
    void PerformChanges(const std::string& WordToFind, const std::string& additionalCommand) {
        if (!found) {
            if (additionalCommand == deleteCommand) {
                std::cout << "There is no such user " << std::endl;
                return;
            }
            addNewUser(WordToFind);
        }
        else {
            if (additionalCommand == deleteCommand) {
                deleteUser(WordToFind);
                return;
            }
            addToExistingUser(WordToFind);
        }
    }
   
};
int main(int argc, char* argv[])
{
    
    Database db;
    if (argc < 2) {
        std::cout << "Please provide a word to find." << std::endl;
        return 0;
    }
    std::string WordToFind;
    WordToFind = argv[1];

    const auto additionalCommand = std::string(argc > 2 ? argv[2] : "");
    if (WordToFind == bread)
    {
        db.clearDatabase();
        std::cout << "all data was cleared" << std::endl;
        return 0;
    }
    db.findName(WordToFind);
    db.PerformChanges(WordToFind,additionalCommand);
}
