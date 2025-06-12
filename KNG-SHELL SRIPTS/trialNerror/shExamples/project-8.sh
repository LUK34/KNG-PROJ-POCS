#!/bin/bash

# This script will give you the information of a flle.

# -----------------------------------------------------------------------------

# If number of arguments is equal to 0
if [ $# -eq 0 ]
then 
	echo "Usage: $0 [File]"
	exit 1
	# If the user havent provided any argument at the time of execution
fi

OWNER=`ls -la $1 | awk '{print $3}'`
SIZE=`ls -la $1 | awk '{print $5}'`
MONTH=`ls -la $1 | awk '{print $6 " " $7}'`
TIME=`ls -la $1 | awk '{print $8}'`



# -----------------------------------------------------------------------------

echo "File Name: $1"
echo
echo "Owner: $OWNER"
echo "Size: $SIZE bytes"
echo "This file was created on $MONTH at $TIME"
echo "File Type: `file $1`"
echo 
echo "Permissions:"


# -----------------------------------------------------------------------------

#Check READABLE permissions
if [ -r $1 ]
then 
	echo "READABLE= TRUE"
else
	echo "READABLE= FALSE"
fi

# -----------------------------------------------------------------------------

#Check WRITABLE permissions
if [ -w $1 ]
then
        echo "WRITABLE= TRUE"
else
        echo "WRITABLE= FALSE"
fi


# -----------------------------------------------------------------------------

#Check EXECUTABLE permissions
if [ -x $1 ]
then
        echo "EXECUTABLE= TRUE"
else
        echo "EXECUTABLE= FALSE"
fi

# -----------------------------------------------------------------------------







