#include <print>
#include <vector>
#include <optional>
class MegaData {
public:
    static const int smallArraySize = 1024;
    static const int bigArraySize = 1024 * 1024;

    float smallArray[smallArraySize];
    double bigArray[bigArraySize];

    MegaData() {
        fillData();
    }

    void fillData() {
        std::fill(smallArray, smallArray + smallArraySize, 42.0f);
        std::fill(bigArray, bigArray + bigArraySize, 42.0);
    }

    bool operator==(const MegaData& other) const {
        return this == &other;
    }
};

class MegaDataPool {
public:
    MegaDataPool(int size) : pool(size), availableObjects(size, true) {}

    int size() const {
        return pool.size();
    }

    int usedSize() const {
        return usedCounter;
    }

    [[nodiscard]] std::optional<std::reference_wrapper<MegaData>> acquire() {
        for (int i = 0; i < pool.size(); i++) {
            if (availableObjects[i]) {
                availableObjects[i] = false;
                usedCounter++;
                return pool[i];
            }
        }
        return std::nullopt;
    }

    void release(MegaData& object) {
        for (int i = 0; i < pool.size(); i++) {
            if (&pool[i] == &object) {
                object.fillData();
                availableObjects[i] = true;
                usedCounter--;
                return;
            }
        }
        std::println("Error with that object");
    }

private:
    std::vector<MegaData> pool;
    std::vector<bool> availableObjects;
    int usedCounter = 0;
};