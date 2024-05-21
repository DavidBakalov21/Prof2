function Delete-File { 
        $filePath = "image.txt"
        Remove-Item -Path $filePath -Force
}
function Create-Image{
    $filePath = "image.txt"
    $content = @"
255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105
153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42
77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140
86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249
255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105
153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42
77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140
86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249
255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105
153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42
77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140
86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249
255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105 255,97,105
153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42 153,178,42
77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140 77,254,140
86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249 86,151,249
"@
Set-Content -Path $filePath -Value $content -NoNewline
}
function Test-Output {
    param (
        [string]$expectedOutput
    )
    $exePath = ".\main.exe"
    $output = & $exePath  $testCase.args
    if ($output -match $expectedOutput) {
        Write-Output "The operation was successful"
    } else {
        Write-Output "Test failed"
        Write-Output "Actual:   '$output'"
    }
}

Delete-File
Create-Image
#First test case
Write-Output "Bad arguments"
$testCases1 = @(
@{args = @("image.txt"); expected = "Error:Please provide all arguments"},
@{args = @(); expected = "Error:Please provide all arguments"}
)
foreach ($testCase in $testCases1) {
    Test-Output -args $testCase.args -expectedOutput $testCase.expected
}
Delete-File
Create-Image
#Second test case
Write-Output "Normal usage"
$testCases2 = @(
@{args = @("image.txt", "255,97,105"); expected = "Image was changed successfully"}
)
foreach ($testCase in $testCases2) {
    Test-Output -args $testCase.args -expectedOutput $testCase.expected
}
Delete-File
Create-Image
#Third test case
Write-Output "Bad colors"
$testCases3 = @(
@{args = @("image.txt", "255bf,974cgb"); expected = "Please, enter existing color for example 255,97,105"},
@{args = @("image.txt", "255,974,105"); expected = "Please, enter existing color for example 255,97,105"}
)
foreach ($testCase in $testCases3) {
    Test-Output -args $testCase.args -expectedOutput $testCase.expected
}

#Fourth test case
Write-Output "Wrong size picture"
$testCases4 = @(
@{args = @("imageBadSize.txt", "255,97,105"); expected = "Please, provide 16x16 image"}
)
foreach ($testCase in $testCases4) {
    Test-Output -args $testCase.args -expectedOutput $testCase.expected
}

#Fifth test case
Write-Output "Wrong colors picture"
$testCases5 = @(
@{args = @("imageBadColor.txt", "255,97,105"); expected = "Please, provide image with valid colors"}
)
foreach ($testCase in $testCases5) {
    Test-Output -args $testCase.args -expectedOutput $testCase.expected
}