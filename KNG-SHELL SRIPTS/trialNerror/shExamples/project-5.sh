#!/bin/bash

# This script can only be run by root user

if [ $UID -eq 0 ]
then
	echo "You have access to this file system."
else
	echo "Login as root user ot access this file."
fi

