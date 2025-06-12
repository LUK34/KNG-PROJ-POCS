#!/bin/bash

# Arthimatic Calculations


echo "Addition of 2 numbers:"
CAL=$((7+10))
echo $CAL

echo "Subtraction of 2 numbers:"
CAL1=$((10-7))
echo $CAL1

echo "Multiplication of 2 numbers:"
CAL2=$((10*7))
echo $CAL2

echo "Division of 2 numbers:"
CAL3=$((10/7))
echo $CAL3

echo "Addition of 2 number using expr:"
CAL4=`expr 7 + 10`
echo $CAL4

echo "Subtrcation of 2 numbers using expr:"
CAL5=`expr 10 - 7`
echo $CAL5

echo "Multiplication of 2 numbers using expr. * is a special character here:"
CAL6=`expr 7 \* 10`
echo $CAL6

echo "Division of 2 numbers using expr:"
CAL7=`expr 10 / 7`
echo $CAL7




