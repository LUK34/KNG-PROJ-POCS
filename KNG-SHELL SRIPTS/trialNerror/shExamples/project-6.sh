#!/bin/bash

# Shell script that processes images.

if [ $# -eq 0 ]
then
	# This will print the file name
	# If there is no argument then the below statement will be executed.
	echo "Usage: $0 [File-1][File-2]...[File-n]"
	exit
fi

for i in $@
do
	if [ -f $i ]
	then
		file_name=`echo $i | sed "s/[.].*//"`
		# If convert (from ImageMagick) is not installed or properly configured,
		# the script will fail. You should ensure the command exists before using it. 
		# You can add a check for convert at the beginning of your script:
		convert $i $file_name.png
	else
		echo "ERROR: $i is not a regular file"
	fi
done

echo "[+] File conversion completed."
