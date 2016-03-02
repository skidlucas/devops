#! /bin/bash

echo -e "Vous avez indiqué que le path de votre projet est le suivant : $1\n"
echo "Nettoyage préalable..." #faire les vérifs nécessaires

rm -rf ./transformation-code/*
mkdir -p ./transformation-code/src/
cp ./pomTransfoDefault.xml ./transformation-code/pom.xml #On copie le pom qui provient de notre projet
echo -e "OK\n"

echo "Création des liens symboliques..." #faire les vérifs nécessaires
absolutePath=($(readlink -f $1))
files=($(readlink -f $1/src/*))
for file in "${files[@]}"
    do
        ln -sv $file ./transformation-code/src/
    done
echo -e "OK\n"


#On fusionne les deux pom.xml en important les dépendances et les plugins du src à muter
echo "Importation des dépendances et des plugins du projet à muter..."
matchDep='<dependencies>'
matchPlu='<plugins>'

sed -e '/<dependency>/,/<\/dependency>/!d' $absolutePath/pom.xml > ./script/dependencies.txt
sed -i "/$matchDep/r dependencies.txt" ./transformation-code/pom.xml

sed -e '/<plugin>/,/<\/plugin>/!d' $absolutePath/pom.xml > ./script/plugins.txt
sed -i "/$matchPlu/r plugins.txt" ./transformation-code/pom.xml

echo -e "OK\n"


echo "Compilation des processeurs..."
mkdir -p "Maven Logs"
mvn package -pl processors > "./Maven Logs/processors.txt"
echo -e "OK\n"

#Permet à l'utilisateur de choisir les processeurs à invoquer
./script/choice.sh 