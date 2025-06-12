#!/bin/bash

# While loop
# Piping the names.txt to output where while loop is executed.

NUM=1
cat countries.txt |
# the below command will read the name line by line. Here `line` is a variable
while read line
do 
	echo $NUM.$line
	let NUM++
done
