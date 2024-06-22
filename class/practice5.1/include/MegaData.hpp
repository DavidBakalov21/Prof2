#include <print>
#include <vector>
#include <optional>
#include <thread>
#include <mutex>
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

    static MegaDataPool& getInstance(int size=5) {
        static MegaDataPool instance(size);
        return instance;
    }


    int size() const {
        std::lock_guard<std::mutex> lock(mutex);
        return pool.size();
    }

    int usedSize() const {
        std::lock_guard<std::mutex> lock(mutex);
        return std::count(availableObjects.begin(), availableObjects.end(), false);
    }

    [[nodiscard]] std::optional<std::reference_wrapper<MegaData>> acquire() {
        std::lock_guard<std::mutex> lock(mutex);
        for (int i = 0; i < pool.size(); i++) {
            if (availableObjects[i]) {
                availableObjects[i] = false;
                return pool[i];
            }
        }
        return std::nullopt;
    }

    bool release(MegaData& object) {
        std::lock_guard<std::mutex> lock(mutex);
        for (int i = 0; i < pool.size(); i++) {
            if (&pool[i] == &object) {
                object.fillData();
                availableObjects[i] = true;
                return true;
            }
        }
        return false;
    }

private:
    MegaDataPool(int size) : pool(size), availableObjects(size, true) {}
    MegaDataPool(const MegaDataPool&) = delete;
    MegaDataPool& operator=(const MegaDataPool&) = delete;
    std::vector<MegaData> pool;
    std::vector<bool> availableObjects;
    mutable std::mutex mutex;
};

std::optional<std::reference_wrapper<MegaData>> acquireFromPool() {
    return MegaDataPool::getInstance().acquire();
}

bool releaseToPool(MegaData& object) {
    return MegaDataPool::getInstance().release(object);
}

int getPoolSize() {
    return MegaDataPool::getInstance().size();
}

int getUsedPoolSize() {
    return MegaDataPool::getInstance().usedSize();
}