#! /bin/bash

#On ajoute le processeur dans le pom.xml
sed -i "s/\(<processor>\).*\(<\/processor>\)/\1$1\2/" ./transformation-code/pom.xml

#On ajoute le pourcentage au code java
sed -i "s/K_LIKELIHOOD = .*;/K_LIKELIHOOD = $2;/"  ./processors/src/main/java/AbstractProc.java

echo "Exécution des tests sur le code source avec les mutations réalisées par le processeur $1 à $2%..."
#On lance mvn clean test qui va se servir de spoon pour muter le code source fourni en paramètre du script
mvn test -pl transformation-code > "./Maven Logs/codeWith$1.txt"
echo -e "OK"

tests=($(find ./transformation-code/src/test/java/ -type f -iname "*.java"))
for test in ${tests[@]}
	do
		packageName=($(grep "^package" $test | cut -d " " -f2 | head -c -2))
		directory=($(dirname $test))
		testName=($(basename $test))

		#Vérifie la taille du nom du test en tokens séparés par des .
		IFS='.' read -r -a name <<< "$testName"
		if [ "${#name[@]}" -ge 3 ]; then #S'il y a plus de 3 tokens, on renomme pas
			:
		else #sinon on renomme
			if [[ -z "$packageName" ]]; then
				:
			else	
				mv $test $directory/$packageName.$testName
			fi
		fi
	done
#On exécute un script python pour lire le rapport XML des tests et l'inserer dans un .txt
echo "Traitement des résultats..."
./python/data.py $1 $2
echo -e "OK\n"

#On remet les fichiers de test comme ils étaient
newtests=($(find ./transformation-code/src/test/java/ -type f -iname "*.java"))
for newtest in ${newtests[@]}
	do
		directory=($(dirname $newtest))
		name=($(basename $newtest | rev | cut -d '.' -f2 | rev))
		ext=($(basename $newtest | rev | cut -d '.' -f1 | rev))
		realName=$name.$ext
		
		mv $newtest $directory/$realName 2> /dev/null
	done

rm -f ./python/report/logMutation.txt
rm -f ./transformation-code/target/surefire-reports/*