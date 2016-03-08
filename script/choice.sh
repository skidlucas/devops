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
selectorChosen=()

select proc in "${choice[@]}"
do
    case $proc in
        "Lancer le framework")
            if [ ${#procChosen[@]} -eq 0 ]; then #si la taille de procChosen est 0
                echo "Aucun processeur choisi, veuillez en choisir un au minimum."
            else
                echo -e "Lancement du framework de tests par mutation...\n"
                break
            fi
            ;;
        "Quitter")
            rm -f ./script/dependencies.txt ./script/plugins.txt ./script/selector.txt ./script/repositories.txt
            exit 1
            ;;
        "Tous les processeurs")
            echo "Vous avez choisi tous les processeurs."
            procChosen=("${procArray[@]}")
            
            #Meme sélecteur pour tous les processeurs
            selectorChosen=()
            ./script/selector.sh
            selector=($(cat ./script/selector.txt))
            for ((i=0;i<${#procChosen[@]};i++)); 
                do
                    selectorChosen=("${selectorChosen[@]}" $selector)
                done
            ;;
        $proc)
            if [[ -z "$proc" ]]; then #vérifie que $proc n'est pas nul
                echo "Option incorrecte."
            else
                echo "Vous avez choisi $proc."
                if [[ " ${procChosen[@]} " =~ " ${proc} " ]]; then #vérifie que procChosen ne contient pas déjà proc
                    echo "Vous avez déjà choisi ce processeur."
                else
                    procChosen=("${procChosen[@]}" $proc)

                    #On choisit les sélecteurs (pourcentage)
                    ./script/selector.sh
                    selector=($(cat ./script/selector.txt))
                    selectorChosen=("${selectorChosen[@]}" $selector)
                fi
            fi
            ;;
        *) echo 'Option incorrecte.';;
    esac
done

#On init le .html
./python/initHTML.py

for ((i=0;i<${#procChosen[@]};i++)); 
    do
        ./script/process.sh "${procChosen[i]}" "${selectorChosen[i]}"
    done

#Fin
./script/end.sh
