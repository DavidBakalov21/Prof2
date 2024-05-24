#!/bin/bash

delete_file() {
    local filePath="database.txt"
    rm -f $filePath
}

execute_test() {
    local exe_path=$1
    local expected_output=$2
    shift 2
    local args=("$@")

    local output=$($exe_path "${args[@]}")
    local status=$?
    if echo "$output" | grep -qE "$expected_output"; then
        echo "The operation was successful for $exe_path"
    else
        echo "Test failed for $exe_path"
        echo "Expected:'$expected_output'"
        echo "Actual:'$output'"
    fi
}
run_test_cases() {
    local test_cases=("$@")
    for test_case in "${test_cases[@]}"; do
        IFS="|" read -r -a case_parts <<< "$test_case"
        IFS=" " read -r -a args <<< "${case_parts[0]}"
        execute_test "./adv" "${case_parts[1]}" "${args[@]}"       
    done
    delete_file
    for test_case in "${test_cases[@]}"; do
        IFS="|" read -r -a case_parts <<< "$test_case"
        IFS=" " read -r -a args <<< "${case_parts[0]}"
        execute_test "java AdvancedJava" "${case_parts[1]}" "${args[@]}"   
    done
    delete_file
}
delete_file
# First test case
echo "Normal usage"
test_cases1=(
    "Vitalik|Welcome Vitalik"
    "Vitalik|Hello again\(x2\),Vitalik"
    "Vitalik|Hello again\(x3\),Vitalik"
)
run_test_cases "${test_cases1[@]}"


# Second test case
echo "Normal usage then delete user"
test_cases2=(
    "David|Welcome David"
    "David|Hello again\(x2\),David"
    "David delete|All data about this user was cleared"
)
run_test_cases "${test_cases2[@]}"

# Third test case
echo "Usage after deletion"
test_cases3=(
    "David|Welcome David"
    "David delete|All data about this user was cleared"
    "David|Welcome David"
    "David|Hello again\(x2\),David"
    "David|Hello again\(x3\),David"
)
run_test_cases "${test_cases3[@]}"
# Fourth test case
echo "Few users added then bread"
test_cases4=(
    "VitalikII|Welcome VitalikII"
    "VitalikII|Hello again\(x2\),VitalikII"
    "VitalikII|Hello again\(x3\),VitalikII"
    "Sasha|Welcome Sasha"
    "Sasha|Hello again\(x2\),Sasha"
    "Sasha|Hello again\(x3\),Sasha"
    "bread|all data was cleared"
)
run_test_cases "${test_cases4[@]}"

# Fifth test case
echo "Usage after bread usage"
test_cases5=(
    "VitalikII|Welcome VitalikII"
    "Sasha|Welcome Sasha"
    "bread|all data was cleared"
    "VitalikII|Welcome VitalikII"
    "VitalikII|Hello again\(x2\),VitalikII"
    "VitalikII|Hello again\(x3\),VitalikII"
    "Sasha|Welcome Sasha"
    "Sasha|Hello again\(x2\),Sasha"
    "Sasha|Hello again\(x3\),Sasha"
)
run_test_cases "${test_cases5[@]}"

# Sixth test case
echo "Nonsense arguments"
test_cases6=(
    "Vitalik ddfv sdsdvs|Welcome Vitalik"
    "Vitalik ddfv sdsdvs gdyjrth|Hello again\(x2\),Vitalik"
    "|Please provide a name to find."
)
run_test_cases "${test_cases6[@]}"
