#include <print>
#include <Helpers.hpp>
#include <UnitTests.hpp>

int main() {
    UnitTests testSuite;

    testSuite.addTest("Dummy_test1",
    [](){
        // Build:
        double value = 0.0;
    
        // Operate:
        auto result = dummyFunc(value);
        ASSERT_EQ(result, 0.0)
       
    });

   testSuite.addTest("Dummy_test2",
    [](){
        double value = 4.0;
        auto result = dummyFunc(value);
        ASSERT_EQ(result,4.0)
    
    });
       testSuite.addTest("Dummy_test3",
    [](){
        double value = -4.0;
        auto result = dummyFunc(value);
        ASSERT_EQ(result,0)
    });
        testSuite.addTest("Dummy_test4",
    [](){
        double value = 1;
        auto result = dummyFunc(value);
        ASSERT_EQ(result, 1)
    });
    testSuite.run();
}
