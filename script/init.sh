#! /bin/bash

echo -e "Vous avez indiqué que le path de votre projet est le suivant : $1\n"
echo "Nettoyage préalable..." #faire les vérifs nécessaires

rm -rf ./transformation-code/*
mkdir -p ./transformation-code/src/
cp ./pomTransfoDefault.xml ./transformation-code/pom.xml #On copie le pom qui provient de notre projet
echo -e "OK\n"

echo "Création des liens symboliques..." #faire les vérifs nécessaires
ln -sv $1/src/* ./transformation-code/src/
echo -e "OK\n"


#On fusionne les deux pom.xml en important les dépendances et les plugins du src à muter
echo "Importation des dépendances et des plugins du projet à muter..."
matchDep='<dependencies>'
matchPlu='<plugins>'

sed -e '/<dependency>/,/<\/dependency>/!d' $1/pom.xml > ./script/dependencies.txt
sed -i "/$matchDep/r dependencies.txt" ./transformation-code/pom.xml

sed -e '/<plugin>/,/<\/plugin>/!d' $1/pom.xml > ./script/plugins.txt
sed -i "/$matchPlu/r plugins.txt" ./transformation-code/pom.xml

echo -e "OK\n"


echo "Compilation des processeurs..."
mkdir -p "Maven Logs"
mvn package -pl processors > "./Maven Logs/processors.txt"
echo -e "OK\n"

#On init le .html
./python/initHTML.py