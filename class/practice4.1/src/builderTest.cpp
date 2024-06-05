#include <print>
#include <builder.hpp>
#include <UnitTests.hpp>


int main() {
    UnitTests testSuite;

    testSuite.addTest("10 dollar pizza",
    [](){
        // Build:
        Director director;
        BestPizzaBuilder gamingBuilder;
        director.setBuilder(&gamingBuilder);
        director.constructPizza("mayo", "meat", "mozzarella", "onions", 1,2,3,4);
        Pizza* gamingPizza = gamingBuilder.getPizza();
        
        // Operate:
        auto result =gamingPizza->showPizza();

        //Check:
        ASSERT_EQ(result, "First: mayo Second: meat Third: mozzarella Fourth: onions Price: $10")
       
    });

    testSuite.addTest("0 dollar pizza",
    [](){
        // Build:
        Director director;
        BestPizzaBuilder gamingBuilder;
        director.setBuilder(&gamingBuilder);
        director.constructPizza("nothing", "nothing", "nothing", "nothing", 0,0,0,0);
        Pizza* gamingPizza = gamingBuilder.getPizza();
        
        // Operate:
        auto result =gamingPizza->showPizza();

        //Check:
        ASSERT_EQ(result, "First: nothing Second: nothing Third: nothing Fourth: nothing Price: $0")
       
    });
 testSuite.run();
}