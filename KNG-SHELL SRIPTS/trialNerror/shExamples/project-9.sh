#!/bin/bash

# Server Utilization

# Color
COLOR_RED_BG="\e[1;41m"
COLOR_GREEN_BG="\e[1;42m"
COLOR_YELLOW_BG="\e[1;43m"
COLOR_BLUE_BG="\e[1;44m"
COLOR_MAGENTA_BG="\e[1;45m"
COLOR_CYAN_BG="\e[1;46m"
COLOR_WHITE_BG="\e[1;47m"
EXIT="\e[0m"

divider (){
	echo -e "${COLOR_RED_BG}=======================================================${EXIT}"
}



echo -e "		${COLOR_CYAN_BG}************************************${EXIT}"
echo -e "               ${COLOR_CYAN_BG}******** SERVER UTILIZATION ********${EXIT}"
echo -e "               ${COLOR_CYAN_BG}************************************${EXIT}"

echo
echo -e "Today's date is : `date`"
echo
divider
echo

echo "Uptime: `uptime`"
echo
divider
echo

echo -e  "${COLOR_CYAN_BG} Currently logged on users's ${EXIT}"
w
echo

echo
divider
echo
echo -e "${COLOR_CYAN_BG} Last logins ${EXIT}"

last -a | head -n 3

echo
divider
echo

echo -e "${COLOR_CYAN_BG} Disk and Memory usage ${EXIT}"
df -h | xargs | awk '{print "DISK SPACE -> Free/Total -> " $11 "/" $9 }'
echo
free -m | xargs | awk '{print "MEMORY -> Free/Total -> " $17 "/" $8 " MB"}' 
echo
divider
echo

echo -e "${COLOR_CYAN_BG} Utilization and most expensive processes ${EXIT}"
echo
top -b | head -n 3
echo
top -b | head -n 10 | tail -n 4
echo
divider
echo











