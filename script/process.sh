#! /bin/bash

#On ajoute le processeur dans le pom.xml
sed -i "s/\(<processor>\).*\(<\/processor>\)/\1$1\2/" ./transformation-code/pom.xml

echo "Exécution des tests sur le code source avec les mutations réalisées par le processeur $1..."
#On lance mvn clean test qui va se servir de spoon pour muter le code source fourni en paramètre du script
mvn test -pl transformation-code > "./Maven Logs/codeWith$1.txt"
echo -e "OK\n"

#On exécute un script python pour lire le rapport XML des tests et l'inserer dans un .txt
./python/data.py $1
