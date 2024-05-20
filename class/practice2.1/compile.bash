#!/bin/bash

# pass $@ instead of $1, $2, $3 - it will allow to support variable input arguments count.
clang++ -Wall -Wextra -Wpedantic -Werror -std=c++23 $1 ${2} ${3}