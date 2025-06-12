#!/bin/bash


#If- else | File conditions

#FILE=helloWorld.sh

# -x option is used to check executable permissions of a file

if [ -x $1 ]
then 
	echo "$1 is EXECUTABLE..."
else
	echo "$1 is NOT EXECUTABLE..."
fi
