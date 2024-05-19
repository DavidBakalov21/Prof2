$exePath = "AdvHello"

#First test case
Write-Output "FIRST TEST CASE"
$args = "Vitalik"
$output = java $exePath $args
if ($output -match "Welcome Vitalik") {
    Write-Output "The operation was successful."
}
$output = java $exePath $args
if ($output -match "Hello again\(x2\),Vitalik") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}
$output = java $exePath $args
if ($output -match "Hello again\(x3\),Vitalik") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}

#Second test case
Write-Output "SECOND TEST CASE"
$args = "David"
$output = java $exePath $args
if ($output -match "Welcome David") {
    Write-Output "The operation was successful."
}
$output = java $exePath $args
if ($output -match "Hello again\(x2\),David") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}
$args = "David", "delete"
$output = java $exePath $args
if ($output -match "All data about this user was cleared") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}

#Third test case
Write-Output "THIRD TEST CASE"
$args = "David"
$output = java $exePath $args
if ($output -match "Welcome David") {
    Write-Output "The operation was successful."
}
$output = java $exePath $args
if ($output -match "Hello again\(x2\),David") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}
$output = java $exePath $args
if ($output -match "Hello again\(x3\),David") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}

#Fourth test case
Write-Output "FOURTH TEST CASE"
$args = "VitalikII"
$output = java $exePath $args
if ($output -match "Welcome VitalikII") {
    Write-Output "The operation was successful."
}
$output = java $exePath $args
if ($output -match "Hello again\(x2\),VitalikII") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}
$output = java $exePath $args
if ($output -match "Hello again\(x3\),VitalikII") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}
$args = "Sasha"
$output = java $exePath $args
if ($output -match "Welcome Sasha") {
    Write-Output "The operation was successful."
}
$output = java $exePath $args
if ($output -match "Hello again\(x2\),Sasha") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}
$output = java $exePath $args
if ($output -match "Hello again\(x3\),Sasha") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}
$args = "bread"
$output = java $exePath $args
if ($output -match "all data was cleared") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}

#Fifth test case
Write-Output "FIFTH TEST CASE"
$args = "VitalikII"
$output = java $exePath $args
if ($output -match "Welcome VitalikII") {
    Write-Output "The operation was successful."
}
$output = java $exePath $args
if ($output -match "Hello again\(x2\),VitalikII") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}
$output = java $exePath $args
if ($output -match "Hello again\(x3\),VitalikII") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}
$args = "Sasha"
$output = java $exePath $args
if ($output -match "Welcome Sasha") {
    Write-Output "The operation was successful."
}
$output = java $exePath $args
if ($output -match "Hello again\(x2\),Sasha") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}
$output = java $exePath $args
if ($output -match "Hello again\(x3\),Sasha") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}


#Sixth test case
Write-Output "SIXTH TEST CASE"
$args = "Vitalik", "ddfv","sdsdvs"
$output = java $exePath $args
if ($output -match "Welcome Vitalik") {
    Write-Output "The operation was successful."
}
$args = "Vitalik", "ddfv","sdsdvs", "gdyjrth"
$output = java $exePath $args
if ($output -match "Hello again\(x2\),Vitalik") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}
$output = java $exePath 
if ($output -match "Please provide a word to find.") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}

