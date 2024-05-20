#include <string>
#include <vector>
#include <iostream>
#include <fstream>
#include <sstream>

// Use std::ranges and std::views functionality to split the string into tokens
std::vector<std::string> splitString(const std::string& str, char delimiter) {
    std::vector<std::string> tokens;
    std::stringstream ss(str);
    std::string token;

    while (std::getline(ss, token, delimiter)) {
        tokens.push_back(token);
    }
    return tokens;
}
int main(int argc, char* argv[])
{
    // We should name variables in a way that they are clear and easy to understand based on their purpose.
    std::string argument2;

    // Inconsistent naming style. Also that parameter is a name we provide for a program.
    // Also you should move declaration of variable to the real usage. the same is reasonable for argument2.
    std::string WordToFind;
    if (argc < 2) {
        std::cout << "Please provide a word to find." << std::endl;
        return 0;
    }
    WordToFind= argv[1];

    // const auto someBetterNameForArgument2 = std::string(argc > 2 ? argv[2] : "");
    if (argc>2)
    {
        argument2 = argv[2];
    }

    // You have hardcoded values across the code which causes copy-paste and make your code hard to support.
    // Use anonymous namespace to store constants
    // constexpr auto commandBread = "bread";
    // constexpr auto databaseFile = "database.txt";
    // and so on for other constants.
    if (WordToFind=="bread")
    {
        std::ofstream file("database.txt", std::ios::trunc); 
        file.close();
        std::cout << "all data was cleared" << std::endl;
        return 0;
    }
    std::ifstream file("database.txt");
    std::string line;
    int lineNumber = 0;
    bool found = false;
    std::string lineFound;

    // This logic of work with database should be encapsulated.
    // You should have a separate class to handle database and some API, to get, update, remove and so on
    std::vector<std::string> lines;
 
    while (std::getline(file, line)) {
        lines.push_back(line);
    }
    file.close();
    // use STL algorithms to find the data for example std::ranges or std::views functionality
    for (const std::string& line : lines) {
        lineNumber++;
        if (line != "") {
            if (splitString(line, ' ')[0] == WordToFind)
            {
                found = true;
                lineFound = line;
                break;
            }
        }
    }

    // It should be encapsulated and the code from below should be properly reworked
    if (!found) {
        if (argument2 == "delete") {
            std::cout << "There is no such user " <<std::endl;
            return 0;
        }
        std::cout << "Welcome " << WordToFind << std::endl;
        std::ofstream fileEdit("database.txt", std::ios::app);
        if (!fileEdit.is_open()) {
            std::cerr << "Could not open the file for writing!" << std::endl;
            return 1;
        }
        fileEdit << WordToFind << " 1" << std::endl;
        fileEdit.close();
    }
    else {
        if (argument2 == "delete") {

            for (int i = 0; i < lines.size(); i++)
            {
                if (splitString(lines[i], ' ')[0] == WordToFind)
                {
                    lines.erase(lines.begin() + i);
                }
            }
            std::ofstream fileEdit("database.txt", std::ios::trunc);
            for (const auto& line : lines) {
                if (line != "")
                {
                    fileEdit << line << std::endl;
                }
            }
            std::cout << "All data about this user was cleared" << std::endl;
            return 0;
        }

        std::ofstream fileEdit("database.txt", std::ios::trunc);
        std::vector<std::string> arraySplitted=splitString(lineFound, ' ');
       int num = std::stoi(arraySplitted[1]);
       num++;
       lines[lineNumber - 1] = arraySplitted[0] +" " + std::to_string(num);
       for (const auto& line : lines) {
           if (line != "")
           {
               fileEdit << line << std::endl;
           }
       }
       std::cout << "Hello again(x"<<num <<"),"<< WordToFind << std::endl;
       fileEdit.close();
    }
}
