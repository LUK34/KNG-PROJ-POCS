#!/bin/bash

# String
STR="Welcome to Hello World"
echo ${STR}

# To change a specific word in the string with another word
echo ${STR/Hello/Shell}

# To slice a word based on number of characters present in sentence
echo ${STR::7}
echo ${STR::10}

# To slice from a specific range of characters
echo ${STR:11:22}
