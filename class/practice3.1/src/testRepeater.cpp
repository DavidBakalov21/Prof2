#include <print>
#include <Helpers.hpp>
#include <UnitTests.hpp>
#include <string>
#include <vector>
int main() {
    UnitTests testSuite;

    testSuite.addTest("Repeater_4homes",
    [](){
        // Build:
        std::string res="home";
        std::vector<int> vec={2,3,4};

        // Operate:
        auto result = StringReapeater(vec, res);

        //Check:
        ASSERT_EQ(result, "homehomehomehome");
       
    });

    testSuite.addTest("Repeater_0homes",
    [](){
        // Build:
        std::string res="home";
        std::vector<int> vec={-2,-1,0};

        // Operate:
        auto result = StringReapeater(vec, res);

        //Check:
        ASSERT_EQ(result, "");
       
    });

    testSuite.addTest("Repeater_emptyVector",
    [](){
        // Build:
        std::string res="home";
        std::vector<int> vec={};

        // Operate:
        auto result = StringReapeater(vec, res);

        //Check:
        ASSERT_EQ(result, "");
       
    });
    
    testSuite.addTest("Repeater_negativeNumbers",
    [](){
        // Build:
        std::string res="home";
        std::vector<int> vec={-1,-1,-1,-1};

        // Operate:
        auto result = StringReapeater(vec, res);

        //Check:
        ASSERT_EQ(result, "");
       
    });

    testSuite.addTest("Repeater_emptyString",
    [](){
        // Build:
        std::string res="";
        std::vector<int> vec={2,3,4};

        // Operate:
        auto result = StringReapeater(vec, res);

        //Check:
        ASSERT_EQ(result, "");
       
    });

    testSuite.addTest("Repeater_emptyStringEmptyVec",
    [](){
        // Build:
        std::string res="";
        std::vector<int> vec={};

        // Operate:
        auto result = StringReapeater(vec, res);

        //Check:
        ASSERT_EQ(result, "");
       
    });
    testSuite.run();
}
