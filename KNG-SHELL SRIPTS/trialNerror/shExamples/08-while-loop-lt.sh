#!/bin/bash

# While loop

NUM=1

# While the number is less than 10
while [ $NUM -lt 10 ] 
do 
	echo $NUM
	# increment the variable by 1
	let NUM++
done
