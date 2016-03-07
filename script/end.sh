#! /bin/bash

#On insére notre .txt contenant le rapport dans notre .html
./python/dataHTML.py
#On termine le .html
./python/endHTML.py

#On remet les fichiers de test comme ils étaient
tests=($(find ./transformation-code/src/test/java/ -type f -iname "*.java"))
for test in ${tests[@]}
	do
		directory=($(dirname $test))
		name=($(basename $test | rev | cut -d '.' -f2 | rev))
		ext=($(basename $test | rev | cut -d '.' -f1 | rev))
		realName=$name.$ext
		
		rm $test
		mv $directory/$realName.old $directory/$realName
	done

#On supprime les fichiers temporaires
rm ./script/dependencies.txt ./script/plugins.txt ./script/selector.txt ./script/repositories.txt
rm ./python/report/data.txt
rm ./python/count.txt


echo -e "Exécution du framework terminée.\n"
echo -e "Si vous souhaitez consulter les logs de Maven, ils sont disponibles dans le dossier Maven Logs."
echo -e "Rapport créé, une fenetre de votre navigateur par défaut sera ouverte automatiquement.\n"
#On affiche ce rapport avec le browser par défaut
xdg-open ./python/report/rapMutant.html &