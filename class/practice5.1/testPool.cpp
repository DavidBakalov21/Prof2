#include <UnitTests.hpp>
#include <MegaData.hpp>
int main() {
    UnitTests testSuite;

    testSuite.addTest("Get then release",
    [](){

        // Build:
         MegaDataPool& pool = MegaDataPool::getInstance(3);

         // Operate:
        auto result = acquireFromPool();
        releaseToPool(result.value());

        //Check:
        ASSERT_EQ(getUsedPoolSize(), 0);
        
    });

    testSuite.addTest("Do not release",
    [](){

        // Build:
         MegaDataPool& pool = MegaDataPool::getInstance(3);

         // Operate:
        auto result = acquireFromPool();
        //pool.release(result);

        //Check:
        ASSERT_EQ(getUsedPoolSize(), 1);
        
    });
    testSuite.run();
}