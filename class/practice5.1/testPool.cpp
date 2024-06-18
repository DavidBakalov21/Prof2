#include <UnitTests.hpp>
#include <MegaData.hpp>

int main() {
    UnitTests testSuite;

    testSuite.addTest("Get then release",
    [](){

        // Build:
        MegaDataPool pool(3);

         // Operate:
        auto result = pool.acquire();
        pool.release(result.value());

        //Check:
        ASSERT_EQ(pool.usedSize(), 0);
        
    });

    testSuite.addTest("Do not release",
    [](){

        // Build:
        MegaDataPool pool(3);

         // Operate:
        auto result = pool.acquire();
        //pool.release(result);

        //Check:
        ASSERT_EQ(pool.usedSize(), 1);
        
    });

    testSuite.addTest("No data",
    [](){

        // Build:
        MegaDataPool pool(0);

         // Operate:
        auto result = pool.acquire();

        //Check:
        ASSERT_EQ(result.has_value(), false);
        
    });
    testSuite.run();
}