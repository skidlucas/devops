## Analyse critique de notre framework

###### Notre framework de tests se découpe en plusieurs parties : 
* Les processeurs (spoon).
* L'analyse des resultats des tests (python).
* La production du rapport (python, bash, html, css).
* La fusion du projet cible avec notre environnement (bash).
* Un ensemble de script bash pour orchestrer le tout.

Les processeurs sont au nombre de 8 et sont variés : ils modifient les variables, les boucles et peuvent aussi modifier la visibilité des fonctions. Les sélecteurs sont **probabilistes**, les mutations touchent l'ensemble du code source avec une probabilité choisie. ils **n'offrent donc pas la possibilité de cibler** certaines parties du code en particulier mais **s'adaptent** plus facilement. En effet l'utilisateur ayant le contrôle sur la probabilité de mutation, il pourra la choisir élevée si la taille de son code est restreinte, tandis qu'il sera plus approprié, pour la majeure partie des mutations, qu'elle soit faible pour un code volumineux.

Notre framework offre des fonctionnalités convaincantes et il est **opérationnel** pour les projets Maven, nous l'avons tout d'abord testé avec un code source très basique pour ensuite mettre à l'épreuve le code source de notre projet d'OGL de l'année dernière. Cependant ce travail visant à rendre notre framework **opérationnel** a été fait au détriment de l'architecture. Les parties ne sont pas toujours bien séparées et il souffre donc d'un **manque de modularité**.

Bash, python et fichier texte sont les langages et le format que nous avons choisi, ils nous ont permis d’économiser du temps et de se **concentrer sur les fonctionnalités** de notre framework. Ces choix ne nous ont pas desservi pour développer notre prototype cependant dans un **soucis de performance** il faudrait à l'avenir se tourner vers d'autres langages, par exemple XSL pour la lecture des rapports XML et l'écriture de notre rapport HTML. Le format texte pourra être abandonné pour le format JSON qui devrait nous offrir une plus grande **modularité** dans notre code.

Notre rapport liste pour chaque processeur les fichiers sources modifiés et propose une visualisation de ces modifications. Pour cela nous avons notamment utilisé la commande bash *diff*. De plus un diagramme circulaire nous permet d'afficher le compte de mutant avec leurs états finaux. En revanche nous ne comptons pas le nombre de mutant que chaque test met en erreur ainsi que l'ensemble des tests qui ont 'tué' chaque mutant. Nous considérons tout de même proposer un **rapport utile** à l'utilisateur.