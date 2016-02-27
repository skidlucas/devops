#! /bin/bash

PS3='Choix: '

echo -e 'Choisissez le(s) processeur(s) que vous souhaitez utiliser: \n'
#On parcourt la liste des processeurs et on les ajoute dans un tableau
procArray=($(ls ./processors/src/main/java/ | cut -f1 -d'.'))
procArray=("${procArray[@]}" "Lancer le framework")
procArray=("${procArray[@]}" "Quitter")

procChosen=()

select proc in "${procArray[@]}"
do
    case $proc in
        "Lancer le framework")
            if [ ${#procChosen[@]} -eq 0 ]; then
                echo "Aucun processeur choisi, veuillez en choisir un au minimum."
            else
                break
            fi
            ;;
        "Quitter")
            exit 1
            ;;
        "$proc")
            echo "Vous avez choisi $proc"
            if [[ " ${procChosen[@]} " =~ " ${proc} " ]]; then
                echo "Vous avez déjà choisi ce processeur."
            else
                procChosen=("${procChosen[@]}" $proc)
            fi
            ;;
        *) echo 'Option incorrecte';;
    esac
done


for proc in "${procChosen[@]}"
    do
        ./script/process.sh $proc
    done