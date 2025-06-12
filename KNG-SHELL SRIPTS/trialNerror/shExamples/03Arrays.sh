#!/bin/bash

# Array practice

ARRAY=(One Two Three Four Five)
echo ${ARRAY[0]}
echo ${ARRAY[1]}
echo ${ARRAY[2]}

echo "All items present in the array are as follows using @ symbol:"
echo ${ARRAY[@]}

echo "All items present in the array are as follows using * symbol:"
echo ${ARRAY[*]}

echo "All items INDEX values present in the array are as follows using ! symbol:"
echo ${!ARRAY[@]}

