#include <string>
#include <vector>
#include <iostream>
#include <fstream>
#include <sstream>
#include <ranges>
#include <print>
std::vector<std::string> splitString(const std::string& str, const char delimiter) {
    return str  | std::ranges::views::split(delimiter)
       | std::ranges::views::transform([](auto&& subrange) {
                 return std::string(subrange.begin(), subrange.end());
              })
       | std::ranges::to<std::vector>();
}

class Pixel
{
private:
    std::string color;
public:
    Pixel(const std::string& col):color(col){}
    std::string getColor() const {
        return color;
    }
    void setColor(const std::string& col){
        color=col;
    }
    bool isValid(){
         std::vector<std::string> elements = splitString(color, ',');
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
};

std::string listToStr(const std::vector<Pixel>& list) {
    std::ostringstream oss;
    for (const Pixel& str : list) {
        if (!oss.str().empty()) {
            oss << " ";
        }
        oss << str.getColor();
    }
    return oss.str();
}
namespace {
    const int MatrixSize = 16;
}



class Image {
private:
    std::vector<std::vector<Pixel>> lines;
    std::string file;
    Pixel color;
    int saveFile() {
        std::ofstream fileEdit("Output"+file, std::ios::trunc);
        for (const auto& line : lines) {
            std::string convertedList=listToStr(line);
            if (convertedList != "")
            {
                fileEdit << convertedList << std::endl;
            }
        }
        fileEdit.close();
        return 0;
    }
public:
     Image(const std::string& filePath, const Pixel& colorArgument) : file(filePath), color(colorArgument) {}
    std::vector<std::vector<Pixel>> getLines() const {
        return lines;
    }

    std::string loadMatrix() {
        std::string line;
        std::ifstream pixelMatrix(file);
        while (std::getline(pixelMatrix, line)) {
            std::vector<std::string>  splittedLine = splitString(line, ' ');
            std::vector<Pixel> pixels;
             if (splittedLine.size() != MatrixSize)
            {
                return "Please, provide 16x16 image";
            }
            for (int i = 0; i < splittedLine.size(); i++)
            {
                Pixel pix(splittedLine[i]);
                if (!pix.isValid()) {
                    return "Please, provide image with valid colors";
                }
                pixels.push_back(pix);
            }
            lines.push_back(pixels);
        }
        pixelMatrix.close();
        return "success";
    }
 
    int changeMatrix() {
        for (int i = 0; i < lines.size(); i++)
        {
           
            for (int j = 0; j < lines.size(); j++)
            {
                if (lines[i][j].getColor() == color.getColor()) {
                    if (i - 1 >= 0)
                    {
                        lines[i - 1][j].setColor(color.getColor());
                    }
                    if (j - 1 >= 0)
                    {
                        lines[i][j - 1].setColor(color.getColor());
                    }
                }
            }
        }
        return saveFile();
    }
  
};
int main(int argc, char* argv[]) {
    if (argc < 3)
    {
        std::cout << "Error:Please provide all arguments" << std::endl;
        return 0;
    }
    
    std::string filename = argv[1];
    std::string colrArgument=argv[2];
    Pixel colorPicked(colrArgument);
    Image image(filename,colorPicked);
    if (!colorPicked.isValid()) {
        std::cout << "Please, enter existing color for example 255,97,105" << std::endl;
        return 0;
    }
    std::string loadResult=image.loadMatrix();
    if (loadResult != "success") {
        std::cout << loadResult << std::endl;
        return 0;
    }

    if (image.getLines().size() != MatrixSize)
    {
        std::println("Please, provide {}x{} image", MatrixSize, MatrixSize);
        return 0;
    }
    image.changeMatrix();
    std::cout << "Image was changed successfully" << std::endl;
}