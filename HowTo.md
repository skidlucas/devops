HowTo.md :
Une présentation de votre travail sous la forme d'un tutoriel a suivre.

Le framework est téléchargeable  sur Github.

Une fois le fichier téléchargé et décompressé, Veuillez lancer le script framework.sh disponible à la racine du dossier
en donnant en argument le chemin du fichier source de votre projet. 

./framework.sh PATH_PROJET_

Après le lancement, vous allez être dirigé dans le menu ou vous pouvez choisir vos mutations ainsi que votre sélecteur.
Voici les mutations disponibles :

* BinaryOperatorProcessor : Echange les operateurs binaires. 
Changement des + en -, des - en +, des / en * et des * ou % en /

* DeleteInstrProcessor : Supprime les if et leur corps.
 
* LogicalExpressionProcessor : Echange les opérateurs de comparaison dans les conditions.
Changement des != en ==, des == en != et rajout ou suppression des = pour les >, <, >= et <=

* LoopIterationProcessor : Modifie les conditions de sortie des boucles. Chaque for ou while est parcouru 100 fois.

* UnaryOperatorProcessor : Echange les opérateurs unaires. Supprime les moins unaires et 
inverse les operations d'incrementation et de décrementation.

* VariableConstProcessor : Modifie les valeurs des variables "static". Celles-ci sont initialisées aux "bornes". 
(int = 0x7fffffff, double = 1.7976931348623157E308, string = JE SUIS UN MUTANT, boolean = true)

* VariableNullProcessor : Initialise les objets à null.

* VisibilityProcessor : Modifie la visibilité des fonctions publiques en package.

Vous pouvez choisir un sélecteur pour chaque processeur ou un selecteur général pour l'ensemble des processeurs.

Un sélecteur est une probabilité (varie entre 0 et 100) qui permet d'augmenter ou de diminuer le nombre de mutations dans le code.
Plus le chiffre est elevé plus le nombre de mutations est grand. Pour la valeur 100, toutes les instructions qui sont susceptibles
d'être mutées, le sont.

Après avoir validé votre choix, le framework se lance. Pour chaque mutation sélectionnée, votre projet est parcouru et modifié puis les 
tests sont exécutés. Les détails des compilations sont disponibles dans le dossier Maven Logs.

Quand l'exécution est terminée, votre navigateur s'ouvre

// affichage