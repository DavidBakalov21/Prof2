#include <Dispatcher.hpp>
#include <print>
int main() {
    MessageDispatcher dispatcher;

    dispatcher.subscribeGreen([](const GreenMessage& msg) {
        std::println("GreenMessage:{}, {}",msg.message,msg.counter);
    });
    dispatcher.subscribeBlue([](const BlueMessage& msg) {
        std::println("BlueMessage:{}, {}",msg.val1,msg.val2);
    });
    dispatcher.subscribeOrange([](const OrangeMessage& msg) {
        std::println("OrangeMessage:{}, {}, {}, {}", msg.message1, msg.message2, msg.counter, msg.val);
    });

    std::thread thread1(generateMessages, std::ref(dispatcher));
    std::thread thread2(generateMessages, std::ref(dispatcher));

    thread1.join();
    thread2.join();

    return 0;
}