#! /bin/bash

# Function

func(){
	first_param=$1
	second_param=$2

	result=$((first_param + second_param))
	return $result
}

# Calling the function with parameters
func 10 20


#Capture the result from the function`s return value
result=$?
echo "The result is : $result"

