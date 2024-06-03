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

        //Check:
        ASSERT_EQ(result, 0.0)
       
    });

    testSuite.addTest("Dummy_test2",
    [](){
        // Build:
        double value = 4.0;

        // Operate:
        auto result = dummyFunc(value);

        //Check:
        ASSERT_EQ(result,4.0)
    
    });

    testSuite.addTest("Dummy_test3",
    [](){
        // Build:
        double value = -4.0;

        // Operate:
        auto result = dummyFunc(value);

        //Check:
        ASSERT_EQ(result,0)
    });

        testSuite.addTest("Dummy_test4",
    [](){
        // Build:
        double value = 1;

        // Operate:
        auto result = dummyFunc(value);

        //Check:
        ASSERT_EQ(result, 1)
    });
    testSuite.run();
}
