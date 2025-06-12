#!/bin/bash


#If- else | File conditions

#FILE=helloWorld.sh

# -w option is used to check writable permissions of a file

if [ -w $1 ]
then 
	echo "$1 is WRITABLE..."
else
	echo "$1 is NOT WRITABLE..."
fi
