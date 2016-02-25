#! /bin/bash

echo -e "Framework de Mutation Testing réalisé par Lucas Martinez, Simon Paris et Lucas Soumille\n\n"

if [ $# -eq 0 ]
  	then
	    echo "ERREUR: aucun argument, veuillez indiquer le chemin absolu de votre code à muter en paramètre."
	    echo "Veuillez indiquer la racine du projet et non pas uniquement le dossier src"
	    echo "Exemple: si votre projet est dans /home/user/devops, tapez ./script.sh /home/user/devops"
	    exit 1
fi

if [ $# -eq 1 ]
	then
		echo -e "Vous avez indiqué que le path de votre projet est le suivant : $1\n"
		echo "Nettoyage préalable..." #faire les vérifs nécessaires
		rm -rf ./transformation-code/*
		mkdir -p ./transformation-code/src/
		#cp pomTransfoDefault.xml ./transformation-code/pom.xml #TODO: garder le pom de transfo-code par défaut, puis ajouter les dépendances du source à importer
		echo -e "OK\n"

		echo "Création des liens symboliques..." #faire les vérifs nécessaires
		ln -sv $1/src/* ./transformation-code/src/
		ln -sv $1/pom.xml ./transformation-code/pom.xml #TODO suite: au lieu de lier le pom directement comme ici
		echo -e "OK\n"

		#On parcourt la liste des processeurs et on les ajoute dans un tableau
		procArray=($(ls ./processors/src/main/java/ | cut -f1 -d'.'))

		#On init le .html
		./python/initHTML.py

		#Pour chaque processeur de la liste:
		for proc in ${procArray[@]}
			do 
				#On ajoute le processeur dans le pom.xml
				sed -i "s/\(<processor>\).*\(<\/processor>\)/\1$proc\2/" ./transformation-code/pom.xml

				#On lance mvn clean test qui va se servir de spoon pour muter le code source fourni en paramètre du script
				mvn clean test

				#la suite est TMP : pour l'instant un rapport par mutation

				#On exécute un script python pour lire le rapport XML des tests et l'inserer dans le .html
				./python/incrHTML.py $proc
			done

		#On termine le .html
		./python/endHTML.py
		#On affiche ce rapport avec le browser par défaut
		xdg-open ./python/report/rapMutant.html &

	else
		echo "ERREUR: trop d'argument, veuillez indiquer uniquement le chemin absolu de votre code à muter en paramètre."
	    echo "Veuillez indiquer la racine du projet et non pas uniquement le dossier src"
	    echo "Exemple: si votre projet est dans /home/user/devops, tapez ./script.sh /home/user/devops"
	    exit 1
fi


