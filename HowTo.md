#  HowTo.md : Tutoriel de notre framework.

Le framework est téléchargeable sur Github.

### Lancement

Une fois le fichier téléchargé et décompressé, Veuillez lancer le script framework.sh disponible à la racine du dossier en donnant en argument le chemin de votre projet Maven (c'est-à-dire le dossier contenant les dossiers *src*, *test* et le *pom.xml*).

> ./framework.sh PATH_PROJET

Après le lancement, vous allez être dirigé dans le menu où vous pouvez choisir vos mutations ainsi que votre sélecteur.

### Choix des mutations et des sélecteurs


Si vous choisissez processeur par processeur, vous avez la possibilité de choisir un sélecteur pour chaque processeur, si vous décidez de choisir tous les sélecteurs d'un coup, vous choisirez alors un seul selecteur pour l'ensemble des processeurs.

Un sélecteur est une probabilité (varie entre 1 et 100) qui permet d'augmenter ou de diminuer le nombre de mutations dans le code.
Plus le chiffre est elevé plus le nombre de mutations est grand. Pour la valeur 100, toutes les instructions qui sont susceptibles d'être mutées, le sont.

Après avoir validé votre choix, le framework se lance. Pour chaque mutation sélectionnée, votre projet est parcouru et modifié puis les tests sont exécutés. Les détails des compilations sont disponibles dans le dossier Maven Logs.

Quand l'exécution est terminée, votre navigateur s'ouvre sur le rapport détaillé de l'exécution du framework.

### Affichage du rapport 

Les processeurs de mutation avec leur sélecteur sont réprésentés sous la forme de boutons qui permettent de dérouler la liste des fichiers sources mutés. Le détails des mutations est observable en cliquant sur le fichier que l'on souhaite.
Les symboles "<" représentent le code avant mutation et les ">" après mutation. La ligne est aussi précisée.

Un diagramme donne le résultat global de l'exécution à savoir si les mutants ont été tués ou sont encore vivants. Le décès des mutants est explicité : Les morts nés n'ont pas compilé, les mutants en erreur ont subi une erreur lors de l'exécution et les fails ont été rejetés par les tests. Un mutant est mort si un test est en erreur ou en fail, si tous les tests sont passés, il est considéré vivant.

### Mutations disponibles

* *BinaryOperatorProcessor* : Echange les operateurs binaires. 
Changement des + en -, des - en +, des / en * et des * ou % en /

* *DeleteIfProcessor* : Supprime les if et leur corps.
 
* *LogicalExpressionProcessor* : Echange les opérateurs de comparaison dans les conditions.
Changement des != en ==, des == en != et rajout ou suppression des = pour les >, <, >= et <=

* *LoopIterationProcessor* : Modifie les conditions de sortie des boucles. Chaque for ou while est parcouru 100 fois.

* *UnaryOperatorProcessor* : Echange les opérateurs unaires. Supprime les moins unaires et 
inverse les operations d'incrementation et de décrementation.

* *VariableConstProcessor* : Modifie les valeurs des variables "static". Celles-ci sont initialisées aux "bornes". 
(*int = 0x7fffffff, double = 1.7976931348623157E308, string = JE SUIS UN MUTANT, boolean = true*)

* *VariableNullProcessor* : Initialise les objets à null.

* *VisibilityProcessor* : Modifie la visibilité des fonctions publiques en package.
