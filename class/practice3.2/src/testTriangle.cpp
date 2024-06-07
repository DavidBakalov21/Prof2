#include <print>
#include <Triangle.hpp>
#include <UnitTests.hpp>


int main() {
    UnitTests testSuite;

    testSuite.addTest("Normal use case",
    [](){
        // Build:
        double first = 3;
        double second = 4;
        double third = 5;
        Triangle triangle(first,second,third);

        // Operate:
        auto result = triangle.calculateArea();

        //Check:
        ASSERT_EQ(result, 6)
       
    });

    testSuite.addTest("Case where result has numbers after coma",
    [](){
        // Build:
        double first = 6;
        double second = 2;
        double third = 2;
        Triangle triangle(first,second,third);

        // Operate:
        auto result = triangle.calculateArea();

        //Check:
        ASSERT_EQ(result, -1)
       
    });

    testSuite.addTest("one side is negative",
    [](){
        // Build:
        double first = -1;
        double second = 2;
        double third = 2;
        Triangle triangle(first,second,third);
        
        // Operate:
        auto result = triangle.calculateArea();

        //Check:
        ASSERT_EQ(result, -1)
       
    });
     testSuite.run();
}