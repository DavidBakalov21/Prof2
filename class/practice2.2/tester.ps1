$exePath = ".\main.exe"

#First test case
Write-Output "FIRST TEST CASE"
$args = "image.txt"
$output = & $exePath
if ($output -match "Error:Please provide all arguments") {
    Write-Output "The operation was successful."
}
$output = & $exePath $args
if ($output -match "Error:Please provide all arguments") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}



#Second test case
Write-Output "SECOND TEST CASE"
$args ="image.txt", "255,97,105"
$output = & $exePath $args
if ($output -match "Image was changed successfully") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}


#Third test case
Write-Output "THIRD TEST CASE"
$args ="image.txt", "255bf,974cgb"
$output = & $exePath $args
if ($output -match "Please, enter existing color for example 255,97,105") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}
$args ="image.txt", "255,974,105"
$output = & $exePath $args
if ($output -match "Please, enter existing color for example 255,97,105") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}


#Fourth test case
Write-Output "FOURTH TEST CASE"
$args ="imageBadSize.txt", "255,97,105"
$output = & $exePath $args
if ($output -match "Please, provide 16x16 image") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}

#Fifth test case
Write-Output "FIFTH TEST CASE"
$args ="imageBadColor.txt", "255,97,105"
$output = & $exePath $args
if ($output -match "Please, provide image with valid colors") {
    Write-Output "The operation was successful."
}else{
    Write-Output $output
}