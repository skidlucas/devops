#! /bin/bash

PS3='Choix: '

echo -e 'Choisissez le(s) processeur(s) que vous souhaitez utiliser: \n'
#On parcourt la liste des processeurs et on les ajoute dans un tableau
procArray=($(ls ./processors/src/main/java/*Processor.java | xargs -n1 basename | cut -f1 -d'.'))
choice=("${procArray[@]}")
choice=("${choice[@]}" "Tous les processeurs")
choice=("${choice[@]}" "Lancer le framework")
choice=("${choice[@]}" "Quitter")

procChosen=()

select proc in "${choice[@]}"
do
    case $proc in
        "Lancer le framework")
            if [ ${#procChosen[@]} -eq 0 ]; then
                echo "Aucun processeur choisi, veuillez en choisir un au minimum."
            else
                echo -e "Lancement du framework de tests par mutation...\n"
                break
            fi
            ;;
        "Quitter")
            exit 1
            ;;
        "Tous les processeurs")
            echo "Vous avez choisi tous les processeurs."
            procChosen=("${procArray[@]}")
            ;;
        "$proc")
            echo "Vous avez choisi $proc."
            if [[ " ${procChosen[@]} " =~ " ${proc} " ]]; then
                echo "Vous avez déjà choisi ce processeur."
            else
                procChosen=("${procChosen[@]}" $proc)
            fi
            ;;
        *) echo 'Option incorrecte.';;
    esac
done


for proc in "${procChosen[@]}"
    do
        ./script/process.sh $proc
    done

#Fin
./script/end.sh