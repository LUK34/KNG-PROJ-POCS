#!/bin/bash

# Random Password generator
COLOR_RED_BG="\e[1;41m"
COLOR_GREEN_BG="\e[1;42m"
COLOR_YELLOW_BG="\e[1;43m"
COLOR_BLUE_BG="\e[1;44m"
COLOR_MAGENTA_BG="\e[1;45m"
COLOR_CYAN_BG="\e[1;46m"
COLOR_WHITE_BG="\e[1;47m"
EXIT="\e[0m"


PASS=$(date | sha256sum | cut -c 1-$((RANDOM%5 + 7)))

echo -e "Your new random password is: ${COLOR_CYAN_BG} $PASS ${EXIT}"
