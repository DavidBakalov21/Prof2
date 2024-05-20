$exePath = ".\adv.exe"

# I hope you can do it better with helper function that takes input params string and expected output
# inside the function it will perform necessary checks and will report the case of mismatch.
# at the moment you have a big amount of copypase instead of having list of function calls like
# some_func "Vitalik" "Welcome, Vitalik!"

#First test case
Write-Output "FIRST TEST CASE"
$args = "Vitalik"
$output = & $exePath $args
if ($output -match "Welcome Vitalik") {
    Write-Output "The operation was successful."
}
$output = & $exePath $args
if ($output -match "Hello again\(x2\),Vitalik") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}
$output = & $exePath $args
if ($output -match "Hello again\(x3\),Vitalik") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}

# you should have some function to reset environment and set preconditions
# otherwise the second test case results may depend on the previous runs.
# In other word your test case should be isolated.

#Second test case
Write-Output "SECOND TEST CASE"
$args = "David"
$output = & $exePath $args
if ($output -match "Welcome David") {
    Write-Output "The operation was successful."
}
$output = & $exePath $args
if ($output -match "Hello again\(x2\),David") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}
$args = "David", "delete"
$output = & $exePath $args
if ($output -match "All data about this user was cleared") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}

#Third test case
Write-Output "THIRD TEST CASE"
$args = "David"
$output = & $exePath $args
if ($output -match "Welcome David") {
    Write-Output "The operation was successful."
}
$output = & $exePath $args
if ($output -match "Hello again\(x2\),David") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}
$output = & $exePath $args
if ($output -match "Hello again\(x3\),David") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}

#Fourth test case
Write-Output "FOURTH TEST CASE"
$args = "VitalikII"
$output = & $exePath $args
if ($output -match "Welcome VitalikII") {
    Write-Output "The operation was successful."
}
$output = & $exePath $args
if ($output -match "Hello again\(x2\),VitalikII") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}
$output = & $exePath $args
if ($output -match "Hello again\(x3\),VitalikII") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}
$args = "Sasha"
$output = & $exePath $args
if ($output -match "Welcome Sasha") {
    Write-Output "The operation was successful."
}
$output = & $exePath $args
if ($output -match "Hello again\(x2\),Sasha") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}
$output = & $exePath $args
if ($output -match "Hello again\(x3\),Sasha") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}
$args = "bread"
$output = & $exePath $args
if ($output -match "all data was cleared") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}

#Fifth test case
Write-Output "FIFTH TEST CASE"
$args = "VitalikII"
$output = & $exePath $args
if ($output -match "Welcome VitalikII") {
    Write-Output "The operation was successful."
}
$output = & $exePath $args
if ($output -match "Hello again\(x2\),VitalikII") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}
$output = & $exePath $args
if ($output -match "Hello again\(x3\),VitalikII") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}
$args = "Sasha"
$output = & $exePath $args
if ($output -match "Welcome Sasha") {
    Write-Output "The operation was successful."
}
$output = & $exePath $args
if ($output -match "Hello again\(x2\),Sasha") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}
$output = & $exePath $args
if ($output -match "Hello again\(x3\),Sasha") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}


#Sixth test case
Write-Output "SIXTH TEST CASE"
$args = "Vitalik", "ddfv","sdsdvs"
$output = & $exePath $args
if ($output -match "Welcome Vitalik") {
    Write-Output "The operation was successful."
}
$args = "Vitalik", "ddfv","sdsdvs", "gdyjrth"
$output = & $exePath $args
if ($output -match "Hello again\(x2\),Vitalik") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}
$output = & $exePath 
if ($output -match "Please provide a word to find.") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}

