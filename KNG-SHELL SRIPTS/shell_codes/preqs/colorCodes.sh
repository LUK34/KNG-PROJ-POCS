#!/bin/bash


#background Colors
COLOR_RED_BG="\e[1;41m"
COLOR_GREEN_BG="\e[1;42m"
COLOR_YELLOW_BG="\e[1;43m"
COLOR_BLUE_BG="\e[1;44m"
COLOR_MAGENTA_BG="\e[1;45m"
COLOR_CYAN_BG="\e[1;46m"
COLOR_WHITE_BG="\e[1;47m"
COLOR_BLACK_BG="\e[1;40m"
COLOR_GRAY_BG="\e[1;100m"
COLOR_BRIGHT_RED_BG="\e[1;101m"
COLOR_BRIGHT_GREEN_BG="\e[1;102m"
COLOR_BRIGHT_YELLOW_BG="\e[1;103m"
COLOR_BRIGHT_BLUE_BG="\e[1;104m"
COLOR_BRIGHT_MAGENTA_BG="\e[1;105m"
COLOR_BRIGHT_CYAN_BG="\e[1;106m"
COLOR_BRIGHT_WHITE_BG="\e[1;107m"

# Foreground Colors
COLOR_RED="\e[31m"
COLOR_GREEN="\e[32m"
COLOR_YELLOW="\e[33m"
COLOR_BLUE="\e[34m"
COLOR_MAGENTA="\e[35m"
COLOR_CYAN="\e[36m"
COLOR_WHITE="\e[37m"
COLOR_BLACK="\e[30m"
COLOR_GRAY="\e[90m"
COLOR_BRIGHT_RED="\e[91m"
COLOR_BRIGHT_GREEN="\e[92m"
COLOR_BRIGHT_YELLOW="\e[93m"
COLOR_BRIGHT_BLUE="\e[94m"
COLOR_BRIGHT_MAGENTA="\e[95m"
COLOR_BRIGHT_CYAN="\e[96m"
COLOR_BRIGHT_WHITE="\e[97m"

# Text Styles
TEXT_BOLD="\e[1m"
TEXT_UNDERLINE="\e[4m"
TEXT_BLINK="\e[5m"
TEXT_REVERSE="\e[7m"
TEXT_RESET="\e[0m"

# Reset
EXIT="\e[0m"


# Divider
divider_echo(){
	echo -e "${COLOR_GRAY_BG}=======================================================${EXIT}"
}

header_echo() {
	title_param=$1
	echo -e "${COLOR_CYAN_BG}==== ${title_param} ====${EXIT}"
	return 0 #0 -> SUCCESS
}

highlight_echo()
{
	highlight_param=$1
	highlight_content=$2
	echo -e "${COLOR_GREEN_BG} ${highlight_param} ${highlight_content} is CREATED. ${EXIT}"
	return 0 #0 -> SUCCESS
}

error_echo() {
    error_param=$1
    echo -e "${COLOR_RED_BG} Error: Cannot access ${error_param} ${EXIT}";
    return 1 # 1 -> FAILURE
}


