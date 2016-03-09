#! /bin/bash

#On insére notre .txt contenant le rapport dans notre .html
./python/dataHTML.py
#On termine le .html
./python/endHTML.py

#On remet les fichiers de test comme ils étaient
tests=($(find ./transformation-code/src/test/java/ -type f -iname "*.java"))
for test in ${tests[@]}
	do
		mv $test.old $test
	done

#On supprime les fichiers sources.diff
sources=($(find ./transformation-code/target/generated-sources/spoon/ -type f -iname "*.diff"))
for source in ${sources[@]}
	do
		rm -f $source
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