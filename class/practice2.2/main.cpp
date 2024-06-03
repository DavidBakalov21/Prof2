#include <string>
#include <vector>
#include <iostream>
#include <fstream>
#include <sstream>
#include <ranges>
#include <print>
#include <cstdint>

namespace {
   constexpr const int MatrixSize = 16;
}

std::vector<std::string> splitString(const std::string& str, const char delimiter) {
    return str  | std::ranges::views::split(delimiter)
       | std::ranges::to<std::vector<std::string>>();
}

struct Color {
    int r{ -1 };
    int g{ -1 };
    int b{ -1 };
    };

class Pixel{
public:
    Pixel() = default;
    Pixel(const std::string& col){
       auto values = splitString(col, ',');
       if (values.size() == 3) {
        color.r=convertToInt(values[0]);
        color.g=convertToInt(values[1]);
        color.b=convertToInt(values[2]);
       }
        
    }

    Color getColor() const {
        return color;
    }

    std::string serToString() const{
        std::ostringstream oss;
        oss << (int)color.r<<","
        <<(int)color.g<<","
        <<(int)color.b;
        return oss.str();
    }

    bool isValid() const {
        return (color.r >= 0 && color.r <= 255) &&
            (color.g >= 0 && color.g <= 255) &&
            (color.b >= 0 && color.b <= 255);
    }

    bool compareColor(const Pixel& compare) const {
        return (compare.color.b==color.b) && (compare.color.g==color.g) && (compare.color.r==color.r);
    }

    void setColor(const Pixel& col){
         color = col.getColor();
    }

private:

    int convertToInt(const std::string& str) {
        try {
            return static_cast<int>(std::stoi(str));
        } catch (const std::invalid_argument& ia) {
            return -1;
        } catch (const std::out_of_range& oor) {
            return -1;
        }
    }
    Color color;
};

std::string listToStr(const std::vector<Pixel>& list) {
    std::ostringstream oss;

    for (const Pixel& pix : list) {
        if (!oss.str().empty()) {
            oss << " ";
        }
        oss << pix.serToString();
    }
    return oss.str();
}

class Image {
private:
    std::vector<std::vector<Pixel>> lines;
    std::string pixelMatrixFile;

public:

    Image(const std::string& filePath) : pixelMatrixFile(filePath), lines(MatrixSize, std::vector<Pixel>(MatrixSize)) {}

    int loadMatrix() {
        std::string line;
        std::ifstream pixelMatrix(pixelMatrixFile);
        int indexCounter=0;
        while (std::getline(pixelMatrix, line)) {
            std::vector<std::string>  splittedLine = splitString(line, ' ');
            std::vector<Pixel> pixels(MatrixSize);
             if (splittedLine.size() != MatrixSize)
            {
                return 1;
            }
            for (int i = 0; i < MatrixSize; i++)
            {
                Pixel pix(splittedLine[i]);
                if (!pix.isValid()) {
                    return 2;
                }
                pixels[i]=pix;
            }

            if (indexCounter==MatrixSize)
            {
                return 1;
            }
            
            lines[indexCounter]=pixels;
            indexCounter++;
        }
        pixelMatrix.close();
        return 0;
    }
 
    void changeMatrix(const Pixel& favouriteColor) {
        for (int i = 0; i < MatrixSize; i++)
        {
            for (int j = 0; j < MatrixSize; j++)
            {
                if (lines[i][j].compareColor(favouriteColor)) {
                    if (i > 0)
                    {
                        lines[i - 1][j].setColor(favouriteColor);
                    }
                    if (j > 0)
                    {
                        lines[i][j - 1].setColor(favouriteColor);
                    }
                }
            }
        }
    }

    int saveFile() {
      std::ofstream fileEdit("Output" + pixelMatrixFile, std::ios::trunc);
      for (int i = 0; i < MatrixSize; i++)
      {
          std::string convertedList = listToStr(lines[i]);
          if (convertedList != "")
          {
              fileEdit << convertedList;
              if (i < MatrixSize - 1) { 
                  fileEdit << std::endl;
              }
          }
        }
        fileEdit.close();
        return 0;
    }
};

int main(int argc, char* argv[]) {
    if (argc < 3)
    {
        std::println("Error:Please provide all arguments");
        return 1;
    }
    
    std::string filename = argv[1];
    std::string colrArgument=argv[2];
    Pixel colorPicked(colrArgument);
    Image image(filename);
    if (!colorPicked.isValid()) {
        std::println("Please, enter existing color for example 255,97,105");
        return 1;
    }
    
    int loadResult=image.loadMatrix();
    if (loadResult != 0) {
        switch(loadResult) {
            case 1:
                std::println("Please, provide 16x16 image");
                break;
            case 2:
                std::println("Please, provide image with valid colors");
                break;
            default:
                std::println("Unknown error happened");
                break;

        }
        return 1;
    }

    image.changeMatrix(colorPicked);
    image.saveFile();
    std::println("Image was changed successfully");
}