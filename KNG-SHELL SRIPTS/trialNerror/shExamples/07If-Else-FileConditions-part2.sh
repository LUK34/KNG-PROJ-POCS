#!/bin/bash


#If- else | File conditions

#FILE=helloWorld.sh

# -r option is used to check readable permissions of a file

if [ -r $1 ]
then 
	echo "$FILE is READABLE..."
else
	echo "$FILE is NOT READABLE..."
fi
