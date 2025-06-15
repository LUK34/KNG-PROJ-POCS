#!/bin/bash

for day in $(seq -w 1 15); do
  for month in $(seq -w 1 06); do
    rm -rf ${day}_${month}_2025_*.txt
  done
done
