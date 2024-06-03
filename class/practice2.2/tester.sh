#!/bin/bash
execute_test() {
    expectedOutput=$1
    shift
    exePath="./main"
    output=$($exePath "$@")
    if [[ "$output" =~ $expectedOutput ]]; then
        echo "The operation was successful"
    else
        echo "Test failed"
        echo "Actual:'$output'"
    fi
}
delete_file() {
    local filePath="Outputimage.txt"
    rm -f $filePath
}
run_test_cases() {
    local test_cases=("$@")
    for testCase in "${test_cases[@]}"; do
        IFS='|' read -r -a splitCase <<< "$testCase"
        IFS=' ' read -r -a args <<< "${splitCase[0]}"
        expectedOutput="${splitCase[1]}"
        execute_test "$expectedOutput" "${args[@]}"
    done
}
# First test case
echo "Bad arguments"
testCases1=(
    "image.txt|Error:Please provide all arguments"
    "|Error:Please provide all arguments"
)
run_test_cases "${testCases1[@]}"

# Second test case
echo "Normal usage"
testCases2=(
    "image.txt 255,97,105|Image was changed successfully"
)
run_test_cases "${testCases2[@]}"
original=$(sha256sum imageChecker.txt | awk '{print $1}')  
modified=$(sha256sum Outputimage.txt | awk '{print $1}')
if [ "$original" = "$modified" ]; then
    echo "Files are identical"
else
    echo "Failed, files are different"
fi
# Third test case
echo "Bad colors"
testCases3=(
    "image.txt 255bf,974cgb|Please, enter existing color for example 255,97,105"
    "image.txt 255,974,105|Please, enter existing color for example 255,97,105"
)
run_test_cases "${testCases3[@]}"

# Fourth test case
echo "Wrong size picture"
testCases4=(
    "imageBadSize.txt 255,97,105|Please, provide 16x16 image"
)
run_test_cases "${testCases4[@]}"

# Fifth test case
echo "Wrong colors picture"
testCases5=(
    "imageBadColor.txt 255,97,105|Please, provide image with valid colors"
)
run_test_cases "${testCases5[@]}"

delete_file