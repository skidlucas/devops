#! /bin/bash

echo "-------------------------------------------------------------------------------------------"
echo "|                                                                                         |"
echo -e "| Framework de Mutation Testing réalisé par Lucas Martinez, Simon Paris et Lucas Soumille |"
echo "|                                                                                         |"
echo -e "-------------------------------------------------------------------------------------------\n"

if [ $# -eq 0 ]
  	then
	    echo "ERREUR: aucun argument, veuillez indiquer le chemin de votre code à muter en paramètre."
	    echo "Veuillez indiquer la racine du projet et non pas uniquement le dossier src"
	    echo "Exemple: si votre projet est dans /home/user/devops, tapez ./framework.sh /home/user/devops"
	    exit 1
fi

if [ $# -eq 1 ]
	then
		#Initialisation
		./script/init.sh $1

	else
		echo "ERREUR: trop d'argument, veuillez indiquer uniquement le chemin de votre code à muter en paramètre."
	    echo "Veuillez indiquer la racine du projet et non pas uniquement le dossier src"
	    echo "Exemple: si votre projet est dans /home/user/devops, tapez ./framework.sh /home/user/devops"
	    exit 1
fi


