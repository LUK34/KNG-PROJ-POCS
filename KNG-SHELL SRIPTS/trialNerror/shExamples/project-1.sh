#!/bin/bash

# We will generate a random qoute at every time of execution of this script.
COLOR_RED_BG="\e[1;41m"
COLOR_GREEN_BG="\e[1;42m"
COLOR_YELLOW_BG="\e[1;43m"
COLOR_BLUE_BG="\e[1;44m"
COLOR_MAGENTA_BG="\e[1;45m"
COLOR_CYAN_BG="\e[1;46m"
COLOR_WHITE_BG="\e[1;47m"
EXIT="\e[0m"

ARR=(
"The only way to do great work is to love what you do. – Steve Jobs"
"Success is not final, failure is not fatal: It is the courage to continue that counts. – Winston Churchill"
"Believe you can, and you're halfway there. – Theodore Roosevelt"
"Don't watch the clock; do what it does. Keep going. – Sam Levenson"
"Happiness is not something ready-made. It comes from your own actions. – Dalai Lama"
"The best way to predict the future is to invent it. – Alan Kay"
"Do what you feel in your heart to be right—for you’ll be criticized anyway. – Eleanor Roosevelt"
"In the end, we only regret the chances we didn’t take. – Lewis Carroll"
"Everything you’ve ever wanted is on the other side of fear. – George Addair"
"When you have a dream, you’ve got to grab it and never let go. – Carol Burnett"
)

# $RANDOM s a keyword taht will generater random value.
echo -e "${COLOR_CYAN_BG} ${ARR[$RANDOM % 10]} ${EXIT}"
