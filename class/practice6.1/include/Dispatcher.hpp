#pragma once
#include <string>
#include <vector>
#include <mutex>
#include <functional>
#include <random>
#include <thread>

struct GreenMessage {
    std::string message;
    int counter;
};

struct BlueMessage {
    double val1;
    double val2;
};

struct OrangeMessage {
    std::string message1;
    std::string message2;
    int counter;
    double val;
};
class MessageDispatcher {
public:
    void subscribeGreen(const std::function<void(const GreenMessage&)>& subscriber) {
        std::lock_guard<std::mutex> lock(mutex);
        greenSubscribers.push_back(subscriber);
    }

    void subscribeBlue(const std::function<void(const BlueMessage&)>& subscriber) {
        std::lock_guard<std::mutex> lock(mutex);
        blueSubscribers.push_back(subscriber);
    }

    void subscribeOrange(const std::function<void(const OrangeMessage&)>& subscriber) {
        std::lock_guard<std::mutex> lock(mutex);
        orangeSubscribers.push_back(subscriber);
    }

    void publishGreen(const GreenMessage& message) {
        std::lock_guard<std::mutex> lock(mutex);
        for (const auto& subscriber : greenSubscribers) {
            subscriber(message);
        }
    }

    void publishBlue(const BlueMessage& message) {
        std::lock_guard<std::mutex> lock(mutex);
        for (const auto& subscriber : blueSubscribers) {
            subscriber(message);
        }
    }

    void publishOrange(const OrangeMessage& message) {
        std::lock_guard<std::mutex> lock(mutex);
        for (const auto& subscriber : orangeSubscribers) {
            subscriber(message);
        }
    }

private:
    std::mutex mutex;
    std::vector<std::function<void(const GreenMessage&)>> greenSubscribers;
    std::vector<std::function<void(const BlueMessage&)>> blueSubscribers;
    std::vector<std::function<void(const OrangeMessage&)>> orangeSubscribers;
};


void generateMessages(MessageDispatcher& dispatcher) {
    std::random_device rd;
    std::mt19937 gen(rd());
    std::uniform_int_distribution<> dist(0, 2);

    while (true) {
        int messageType = dist(gen);
        if (messageType == 0) {
            GreenMessage msg = {"Green", dist(gen)};
            dispatcher.publishGreen(msg);
        } else if (messageType == 1) {
            BlueMessage msg = {dist(gen) * 1.0, dist(gen) * 2.0};
            dispatcher.publishBlue(msg);
        } else {
            OrangeMessage msg = {"Orange1", "Orange2", dist(gen), dist(gen) * 1.5};
            dispatcher.publishOrange(msg);
        }
        std::this_thread::sleep_for(std::chrono::milliseconds(100));
    }
}