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
bool colorChecker(std::string& str) {
    std::vector<std::string> elements = splitString(str,',');
    if (elements.size() < 3) {
        return false;
    }
    for (int i = 0; i < elements.size(); i++)
    {
        for (int j = 0; j < elements[i].size(); j++) {
            if (std::isalpha(static_cast<unsigned char>(elements[i][j]))) {
                return false;
            }
        }
        int num = std::atoi(elements[i].c_str());
        if (num < 0 || num >255) {
            return false;
        }
    }
    return true;
}
std::string listToStr(const std::vector<std::string>& list) {
    std::ostringstream oss;
    for (const auto& str : list) {
        if (!oss.str().empty()) {
            oss << " ";
        }
        oss << str;
    }
    return oss.str();
}
int main(int argc, char* argv[]) {
    if (argc < 3)
    {
        std::cout << "Error:Please provide all arguments" << std::endl;
        return 0;
    }
    std::string filename = argv[1];
    std::string color = argv[2];
    if (!colorChecker(color)) {
        std::cout << "Please, enter existing color for example 255,97,105" << std::endl;
        return 0;
    }
    std::string line;
    std::vector<std::vector<std::string>> lines;
    std::ifstream pixelMatrix(filename);
    while (std::getline(pixelMatrix, line)) {
        std::vector<std::string>  splittedLine = splitString(line, ' ');
        for (int i = 0; i < splittedLine.size(); i++)
        {
            if (!colorChecker(splittedLine[i])) {
                std::cout << "Please, provide image with valid colors" << std::endl;
                return 0;
            }
        }
        lines.push_back(splittedLine);
    }
    pixelMatrix.close();

    if (lines.size()!=16)
    {
        std::cout <<"Please, provide 16x16 image" << std::endl;
        return 0;
    }
    for (int i = 0; i < lines.size(); i++)
    {
        if (lines[i].size()!=16)
        {
            std::cout << "Please, provide 16x16 image" << std::endl;
            return 0;
        }
        for (int j = 0; j < lines.size(); j++)
        {
            if (lines[i][j] == color) {
               if (i-1>=0)
                {
                   lines[i - 1][j] = color;
                }
               if (j-1>= 0)
               {
                   lines[i][j - 1] = color;
               }
           }
        }
    }
    std::ofstream fileEdit(filename, std::ios::trunc);
    for (const auto& line : lines) {
        if (listToStr(line) != "")
        {
            fileEdit << listToStr(line) << std::endl;
        }
    }
    fileEdit.close();

    std::cout << "Image was changed successfully" << std::endl;
}