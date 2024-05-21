#include <string>
#include <vector>
#include <iostream>
#include <fstream>
#include <sstream>
#include <ranges>
std::vector<std::string> splitString(const std::string& str, char delimiter) {
    auto view = str | std::ranges::views::split(delimiter) | std::ranges::views::transform([](auto&& subrange) {
        return std::string(subrange.begin(), subrange.end());
        });
    return std::vector<std::string>(view.begin(), view.end());
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
class Image {
private:
    std::vector<std::vector<std::string>> lines;
public:

    std::vector<std::vector<std::string>> getLines() {
        return lines;
    }
    bool colorChecker(std::string& str) {
        std::vector<std::string> elements = splitString(str, ',');
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
    int FillMatrix(std::string& filename) {
        std::string line;
        std::ifstream pixelMatrix(filename);
        while (std::getline(pixelMatrix, line)) {
            std::vector<std::string>  splittedLine = splitString(line, ' ');
            for (int i = 0; i < splittedLine.size(); i++)
            {
                if (!colorChecker(splittedLine[i])) {
                    std::cout << "Please, provide image with valid colors" << std::endl;
                    return 1;
                }
            }
            lines.push_back(splittedLine);
        }
        pixelMatrix.close();
        return 0;
    }
    int changeMatrix(std::string& color) {
        for (int i = 0; i < lines.size(); i++)
        {
            if (lines[i].size() != 16)
            {
                std::cout << "Please, provide 16x16 image" << std::endl;
                return 1;
            }
            for (int j = 0; j < lines.size(); j++)
            {
                if (lines[i][j] == color) {
                    if (i - 1 >= 0)
                    {
                        lines[i - 1][j] = color;
                    }
                    if (j - 1 >= 0)
                    {
                        lines[i][j - 1] = color;
                    }
                }
            }
        }
        return 0;
    }
    int changeFile(std::string& filename) {
        std::ofstream fileEdit(filename, std::ios::trunc);
        for (const auto& line : lines) {
            if (listToStr(line) != "")
            {
                fileEdit << listToStr(line) << std::endl;
            }
        }
        fileEdit.close();
        return 0;
    }
};
int main(int argc, char* argv[]) {
    if (argc < 3)
    {
        std::cout << "Error:Please provide all arguments" << std::endl;
        return 0;
    }
    Image image;
    std::string filename = argv[1];
    std::string color = argv[2];
    if (!image.colorChecker(color)) {
        std::cout << "Please, enter existing color for example 255,97,105" << std::endl;
        return 0;
    }
    if (image.FillMatrix(filename) == 1) {
        return 0;
    }
    if (image.getLines().size() != 16)
    {
        std::cout << "Please, provide 16x16 image" << std::endl;
        return 0;
    }


    if (image.changeMatrix(color) == 1) {
        return 0;
    }
    image.changeFile(filename);

    std::cout << "Image was changed successfully" << std::endl;
}