#include <print>
#include <Helpers.hpp>
#include <UnitTests.hpp>
#include <string>
#include <vector>
int main() {
    UnitTests testSuite;

    testSuite.addTest("Repeater_4homes",
    [](){
        std::string res="home";
        std::vector<int> vec={2,3,4};
        auto result = StringReapeater(vec,res);
        ASSERT_EQ(result, "homehomehomehome");
       
    });

       testSuite.addTest("Repeater_0homes",
    [](){
        std::string res="home";
        std::vector<int> vec={-2,-1,0};
        auto result = StringReapeater(vec,res);
        ASSERT_EQ(result, "");
       
    });

     testSuite.addTest("Repeater_emptyVector",
    [](){
        std::string res="home";
        std::vector<int> vec={};
        auto result = StringReapeater(vec,res);
        ASSERT_EQ(result, "");
       
    });
     testSuite.addTest("Repeater_negativeNumbers",
    [](){
        std::string res="home";
        std::vector<int> vec={-1,-1,-1,-1};
        auto result = StringReapeater(vec,res);
        ASSERT_EQ(result, "");
       
    });
     testSuite.addTest("Repeater_emptyString",
    [](){
        std::string res="";
        std::vector<int> vec={2,3,4};
        auto result = StringReapeater(vec,res);
        ASSERT_EQ(result, "");
       
    });
      testSuite.addTest("Repeater_emptyStringEmptyVec",
    [](){
        std::string res="";
        std::vector<int> vec={};
        auto result = StringReapeater(vec,res);
        ASSERT_EQ(result, "");
       
    });
    testSuite.run();
}
