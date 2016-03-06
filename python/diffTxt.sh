#! /bin/bash

SPOON_DIR=./transformation-code/target/generated-sources/spoon

for file in $(cat ./python/report/logMutation.txt)
do
	#echo $file
	fbname=$(basename "$file" .java)
	#echo "$fbname"
	diff $file ${SPOON_DIR}/${fbname}.java > ./python/report/DIFF_${1}_${fbname}.txt
done