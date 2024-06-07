#include <print>
#include <Color.hpp>
#include <UnitTests.hpp>


int main() {
    UnitTests testSuite;

    testSuite.addTest("Normal case",
    [](){
        // Build:
        double red = 200;
        double green = 100;
        double blue = 5;
        Color col(red,green,blue);

        // Operate:
        auto result = col.createMagicColor().getColor();

        //Check:
        ASSERT_EQ(result, "99,198,5")
       
    });

    testSuite.addTest("Case with invalid color entered",
    [](){
        // Build:
        double red = 200;
        double green = 140;
        double blue = -1;
        Color col(red,green,blue);

        // Operate:
        auto result = col.createMagicColor().getColor();

        //Check:
        ASSERT_EQ(result, "0,0,0")
       
    });

    testSuite.addTest("Magic color is invalid",
    [](){
        // Build:
        double red = 200;
        double green = 240;
        double blue = 5;
        Color col(red,green,blue);
        
        // Operate:
        auto result = col.createMagicColor().getColor();

        //Check:
        ASSERT_EQ(result, "0,0,0")
       
    });
     testSuite.run();
}