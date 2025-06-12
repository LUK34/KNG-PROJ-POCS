#!/bin/bash


#If- else | File conditions

#FILE=helloWorld.sh

# -d option is used to check if a file is a directory or not

if [ -d $1 ]
then 
	echo "$1 is a DIRECTORY..."
else
	echo "$1 is NOT DIRECTORY..."
fi
