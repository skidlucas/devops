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
		mkdir ./transformation-code/src/
		echo -e "OK\n"

		echo "Création des liens symboliques..." #faire les vérifs nécessaires
		ln -sv $1/src/* ./transformation-code/src/
		ln -sv $1/pom.xml ./transformation-code/pom.xml
		echo -e "OK\n"

		mvn clean test
		./python/readXML.py
		xdg-open ./python/rapMutant.html &
	else
		echo "ERREUR: trop d'argument, veuillez indiquer uniquement le chemin absolu de votre code à muter en paramètre."
	    echo "Veuillez indiquer la racine du projet et non pas uniquement le dossier src"
	    echo "Exemple: si votre projet est dans /home/user/devops, tapez ./script.sh /home/user/devops"
	    exit 1
fi


