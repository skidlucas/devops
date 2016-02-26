#! /bin/bash

echo "-------------------------------------------------------------------------------------------"
echo "|                                                                                         |"
echo -e "| Framework de Mutation Testing réalisé par Lucas Martinez, Simon Paris et Lucas Soumille |"
echo "|                                                                                         |"
echo -e "-------------------------------------------------------------------------------------------\n"

if [ $# -eq 0 ]
  	then
	    echo "ERREUR: aucun argument, veuillez indiquer le chemin absolu de votre code à muter en paramètre."
	    echo "Veuillez indiquer la racine du projet et non pas uniquement le dossier src"
	    echo "Exemple: si votre projet est dans /home/user/devops, tapez ./script.sh /home/user/devops"
	    exit 1
fi

if [ $# -eq 1 ]
	then
		#Initialisation
		./script/init.sh $1
	
		# Faire un script qui permet à l'utilisateur de choisir les processeurs à partir de la liste des processeurs
		# ex : ./script/choice.sh 
		# lance un menu shell qui fait choisir un processeur et passe ce processeur en paramètre du prochain script


		#On parcourt la liste des processeurs et on les ajoute dans un tableau
		procArray=($(ls ./processors/src/main/java/ | cut -f1 -d'.'))

		#Pour chaque processeur de la liste:
		for proc in ${procArray[@]}
			do 
				#On ajoute le processeur dans le pom.xml
				sed -i "s/\(<processor>\).*\(<\/processor>\)/\1$proc\2/" ./transformation-code/pom.xml

				echo "Exécution des tests sur le code source avec les mutations réalisées par le processeur $proc..."
				#On lance mvn clean test qui va se servir de spoon pour muter le code source fourni en paramètre du script
				mvn test -pl transformation-code > "./Maven Logs/codeWith$proc.txt"
				echo -e "OK\n"

				#On exécute un script python pour lire le rapport XML des tests et l'inserer dans un .txt
				./python/data.py $proc
			done
			
		./script/end.sh

	else
		echo "ERREUR: trop d'argument, veuillez indiquer uniquement le chemin absolu de votre code à muter en paramètre."
	    echo "Veuillez indiquer la racine du projet et non pas uniquement le dossier src"
	    echo "Exemple: si votre projet est dans /home/user/devops, tapez ./script.sh /home/user/devops"
	    exit 1
fi


