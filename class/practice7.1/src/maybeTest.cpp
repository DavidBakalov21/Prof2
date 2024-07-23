#include <UnitTests.hpp>
#include <Maybe.hpp>
int main() {
    UnitTests testSuite;

    testSuite.addTest("Get area",
    [](){
        Rect rect(3.0, 7.0);
        ASSERT_EQ(rect.getArea(), 21.0)
    });

    testSuite.addTest("Get biggest side",
    [](){
        Rect rect(3.0, 7.0);
        ASSERT_EQ(rect.getBiggestSide(), 7.0)
    });
    testSuite.addTest("Test fits",
    [](){
        Rect rect(3.0, 7.0);
        Rect rect2(1.0, 2.0);
        ASSERT_EQ(rect2.fitsInside(rect), true)
    });
    testSuite.addTest("Smallest area test",
    [](){
        RectCollection collection;
        collection.addRectangle(Rect(5.0, 4.0));
        collection.addRectangle(Rect(5.0, 6.0));
        ASSERT_EQ(collection.getSmallestAreaRectangle()->getArea(), 20.0)
    });
    testSuite.run();
}