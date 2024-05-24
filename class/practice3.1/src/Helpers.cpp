#include "Helpers.hpp"

double dummyFunc(double x)
{
    return x > 0 ? x : 0;
}
std::string StringReapeater(std::vector<int> vec, std::string str)
{
    if (vec.size()<1)
    {
    return "";
    }
    int max = *(std::ranges::max_element(vec));
    std::ostringstream oss;
    if (max<1)
    {
        return "";
    }
    for (int i = 0; i < max; i++)
    {
        oss << str;
    }
    return oss.str();
}
