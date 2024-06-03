#include "Helpers.hpp"

double dummyFunc(double x)
{
    return x > 0 ? x : 0;
}

std::string StringReapeater(const std::vector<int>& vec, const std::string& str)
{
auto repeatCount = vec.empty() ? 0 : std::ranges::max(vec);
return repeatCount > 0
            ? std::views::repeat(str, repeatCount) | std::views::join | std::ranges::to<std::string>()
            : "";
}
