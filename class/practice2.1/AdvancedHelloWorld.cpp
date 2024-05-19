#include <string>
#include <vector>
#include <iostream>
#include <fstream>
#include <sstream>

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
    std::string argument2;
        
    std::string WordToFind;
    if (argc < 2) {
        std::cout << "Please provide a word to find." << std::endl;
        return 0;
    }
    WordToFind= argv[1];
    if (argc>2)
    {
        argument2 = argv[2];
    }
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

    std::vector<std::string> lines;
 
    while (std::getline(file, line)) {
        lines.push_back(line);
    }
    file.close();
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
