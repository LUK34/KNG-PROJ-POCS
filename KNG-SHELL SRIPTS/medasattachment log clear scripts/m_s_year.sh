#!/bin/bash

for year in $(seq -w 1 2025): do
   for month in $(seq -w 1 06): do
	rm -rf ${year}${month}
   done
done
