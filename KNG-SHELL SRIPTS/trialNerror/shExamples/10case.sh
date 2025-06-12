#!/bin/bash

# Case
# Take the user input and if the user is root then only he has access to it.

echo "Enter your name: "
read NAME

case $NAME in
	"root" )
		echo "You have access to the folder."
	;;
	"vivek" )
		echo "You don't have access to the folder."
	;;
	* )
		echo "NO ACCESS TO FOLDER. USER UNAUTHORIZED"
esac
