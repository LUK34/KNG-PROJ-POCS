#for-loop-array.sh

#!/bin/bash

# For loop

ARR=( Mercury Venus Earth Mars Jupiter Saturn Uranus Neptune)

for i in ${ARR[@]}
do
	echo $i
done