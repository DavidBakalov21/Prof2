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

    const Rect* getBiggestAreaRectangle() const {
        if (rectangles.empty()) {
            return nullptr;
        }
        return &(*std::max_element(rectangles.begin(), rectangles.end(), [](const Rect& a, const Rect& b) {
            return a.getArea() < b.getArea();
        }));
    }

    const Rect* getSmallestAreaRectangle() const {
        if (rectangles.empty()) {
            return nullptr;
        }
        return &(*std::min_element(rectangles.begin(), rectangles.end(), [](const Rect& a, const Rect& b) {
            return a.getArea() < b.getArea();
        }));
    }

    double getTotalArea() const {
        double totalArea = 0;
        for (const Rect& rectangle : rectangles) {
            totalArea += rectangle.getArea();
        }
        return totalArea;
    }

    int getRectNum(){
        return rectangles.size();
    }

    std::vector<Rect> getCollection(){
        return rectangles;
    }

private:
    std::vector<Rect> rectangles;
};