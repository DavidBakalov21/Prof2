
function Delete-File { 
        $filePath = "database.txt"
        Remove-Item -Path $filePath -Force
}

function Test-Output {
    param (
        [string]$expectedOutput
    )
    $exePath = ".\adv.exe"
    $output = & $exePath  $testCase.args
    if ($output -match $expectedOutput) {
        Write-Output "The operation was successful"
    } else {
        Write-Output "Test failed"
        Write-Output "Actual:   '$output'"
    }
}
Delete-File
#First test case
Write-Output "Normal usage"
$testCases1 = @(
@{args = @("Vitalik"); expected = "Welcome Vitalik"},
@{args = @("Vitalik"); expected = "Hello again\(x2\),Vitalik"},
@{args = @("Vitalik"); expected = "Hello again\(x3\),Vitalik"}
)
foreach ($testCase in $testCases1) {
    Test-Output -args $testCase.args -expectedOutput $testCase.expected
}

Delete-File
#Second test case
Write-Output "Normal usage then delete user"
$testCases2 = @(
@{args = @("David"); expected = "Welcome David"},
@{args = @("David"); expected = "Hello again\(x2\),David"},
@{args = @("David", "delete"); expected = "All data about this user was cleared"}
)
foreach ($testCase in $testCases2) {
    Test-Output -args $testCase.args -expectedOutput $testCase.expected
}
Delete-File
#Third test case
Write-Output "Usage after deletion"
$testCases3 = @(
@{args = @("David"); expected = "Welcome David"},
@{args = @("David", "delete"); expected = "All data about this user was cleared"},
@{args = @("David"); expected = "Welcome David"},
@{args = @("David"); expected = "Hello again\(x2\),David"},
@{args = @("David"); expected = "Hello again\(x3\),David"}
)
foreach ($testCase in $testCases3) {
    Test-Output -args $testCase.args -expectedOutput $testCase.expected
}
Delete-File
#Fourth test case
Write-Output "Few users added then bread"
$testCases4 = @(
@{args = @("VitalikII"); expected = "Welcome VitalikII"},
@{args = @("VitalikII"); expected = "Hello again\(x2\),VitalikII"},
@{args = @("VitalikII"); expected = "Hello again\(x3\),VitalikII"},
@{args = @("Sasha"); expected = "Welcome Sasha"},
@{args = @("Sasha"); expected = "Hello again\(x2\),Sasha"},
@{args = @("Sasha"); expected = "Hello again\(x3\),Sasha"},
@{args = @("bread"); expected = "all data was cleared"}
)
foreach ($testCase in $testCases4) {
    Test-Output -args $testCase.args -expectedOutput $testCase.expected
}
Delete-File
#Fifth test case
Write-Output "Usage after bread usage"
$testCases5 = @(
@{args = @("VitalikII"); expected = "Welcome VitalikII"},
@{args = @("Sasha"); expected = "Welcome Sasha"}
@{args = @("bread"); expected = "all data was cleared"},    
@{args = @("VitalikII"); expected = "Welcome VitalikII"},
@{args = @("VitalikII"); expected = "Hello again\(x2\),VitalikII"},
@{args = @("VitalikII"); expected = "Hello again\(x3\),VitalikII"},
@{args = @("Sasha"); expected = "Welcome Sasha"},
@{args = @("Sasha"); expected = "Hello again\(x2\),Sasha"},
@{args = @("Sasha"); expected = "Hello again\(x3\),Sasha"}
)
foreach ($testCase in $testCases5) {
    Test-Output -args $testCase.args -expectedOutput $testCase.expected
}
Delete-File
#Sixth test case
Write-Output "Nonsense arguements"
$testCases6 = @(
@{args = @("Vitalik", "ddfv","sdsdvs"); expected = "Welcome Vitalik"},
@{args = @("Vitalik", "ddfv","sdsdvs", "gdyjrth"); expected = "Hello again\(x2\),Vitalik"},
@{args = @(""); expected = "Please provide a word to find."}
)
foreach ($testCase in $testCases6) {
    Test-Output -args $testCase.args -expectedOutput $testCase.expected
}

