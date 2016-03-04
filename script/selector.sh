#! /bin/bash

echo -n "Probabilité d'application de la mutation: "
read selector
case $selector in
    $selector)
        if [[ -z "$selector" ]]; then
            echo "Probabilité nulle."
            ./script/selector.sh
        else
            if [[ "$selector" =~ ^[0-9]+$ ]]; then
                if [ "$selector" -ge 1 -a "$selector" -le 100 ]; then 
                    echo "Probabilité choisie: $selector%"
                    echo "$selector" > ./script/selector.txt
                else
                    echo "Probabilité inférieure à 1 ou supérieure à 100."
                    ./script/selector.sh
                fi
            else 
                echo "$selector n'est pas un entier."
                ./script/selector.sh
            fi
        fi
esac