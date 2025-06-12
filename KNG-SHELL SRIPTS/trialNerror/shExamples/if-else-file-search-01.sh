#!/bin/bash

# If-else | File conditions

FILE=helloWorld.sh

# -e option is used to check that file is present in the current directory or not

if [ -e $FILE ]
then
	echo "$FILE is present in the current directory..."
else
	echo "$FILE is not present in the current directory ..."
fi

