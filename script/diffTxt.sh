#! /bin/bash

SPOON_DIR=($(find ./transformation-code/target/generated-sources/spoon -type f -iname "*.java" | xargs -n1 dirname | sort -u))

for dir in ${SPOON_DIR[@]}
do
	for file in $(cat ./python/report/logMutation.txt)
	do
		fname=($(basename "$file"))
		count=($(cd $dir && ls $fname 2>/dev/null | wc -l))

		if [ $count -gt 0 ]; then 
			fbname=$(basename "$file" .java)
			diff $dir/$fbname.java.diff $dir/$fbname.java > ./python/report/diff/DIFF_$1_$fbname.txt
		fi
		
	done
done