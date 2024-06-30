class Rect {
public:
    Rect(double width, double height) : width(width), height(height) {}

    double getArea() const {
        return width * height;
    }

    double getBiggestSide() const {
        return std::max(width, height);
    }

    bool fitsInside(const Rect& other) const {
        return width <= other.width && height <= other.height;
    }

    double getWidth() const {
        return width;
    }

    double getHeight() const {
        return height;
    }

private:
    double width;
    double height;
};

class RectCollection {
public:
    void addRectangle(const Rect& rectangle) {
        rectangles.push_back(rectangle);
    }

    const Rect& getBiggestAreaRectangle() const {
        return *std::max_element(rectangles.begin(), rectangles.end(), [](const Rect& a, const Rect& b) {
            return a.getArea() < b.getArea();
        });
    }

    const Rect& getSmallestAreaRectangle() const {
        return *std::min_element(rectangles.begin(), rectangles.end(), [](const Rect& a, const Rect& b) {
            return a.getArea() < b.getArea();
        });
    }

    double getTotalArea() const {
        double totalArea = 0;
        for (const Rect& rectangle : rectangles) {
            totalArea += rectangle.getArea();
        }
        return totalArea;
    }

    void printFitInformation() const {
        for (int i = 0; i < rectangles.size(); i++) {
            for (int j = 0; j < rectangles.size(); j++) {
                if (i != j && rectangles[i].fitsInside(rectangles[j])) {
                    std::println("Rectangle {} can be placed inside Rectangle {}", i + 1, j + 1);
                }
            }
        }
    }
    void biggestSidePrint(){
        for (int i = 0; i < 5; i++) {
            std::println("The biggest side of rectangle {} : {}", i + 1,rectangles[i].getBiggestSide());
        }
    }

    int getRectNum(){
        return rectangles.size();
    }

private:
    std::vector<Rect> rectangles;
};