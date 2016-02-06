# Spécifications du projet

Pour réaliser l'intégralité du projet, nous allons utiliser les outils suivants :
* Maven
* Spoon
* JUnit
* Bootstrap
* HighChart
* Java
* Bash
* Python

## Description de notre chaîne de build
Nous allons utiliser un script bash pour automatiser nos différentes étapes. Nous commençons par compter le nombre de lignes de code présentes dans notre code source. Cela va nous permettre de fixer une probabilité de mutation pour chaque mutation que nous allons énoncer ci-après. Nous allons aussi fixer le nombre de mutants à faire pour chaque type de mutation. Les raisons de ce choix sont approfondies plus bas.

Nous allons nous servir d'une partie des phases de Maven dans la deuxième étape, plus précisement des phases de build suivantes :
* validate
* compile
* test

Nous allons modifier uniquement les phases _compile_ et _test_. Ainsi nous pourrons répondre à notre problématique tout en profitant de l'automatisation que nous offre Maven.  
Dans la phase _compile_, nous allons modifier en premier lieu la sous-phase _generate-sources_ pour insérer l'outil **Spoon**, en effet, c'est à ce niveau-là que nous avons besoin de générer des mutants. Par la suite, il nous faudra spécifier à Maven que c'est les mutants qu'il doit compiler et non le code source qu'il nous ait fourni. Il est possible qu'il y ait d'autres sous-phases à modifier pour arriver à nos fins.

Concernant la phase _test_, Maven va pouvoir soumettre nos mutants compilés au banc de test JUnit. Il faut qu'on puisse exclure des tests JUnit en fonction du mutant à tester pour pouvoir proposer une stratégie valable d'un point de vue performance. En effet, il paraît déraisonnable d'appliquer à chaque mutant l'ensemble du banc de test. L'idéal serait de pouvoir déterminer, pour un mutant donné, les tests qui concernent le code qui a été muté. Une fois ces tests lancés, nous pourrons ainsi dire avec certitude que les tests au vert sont de mauvaise qualité, sous réserve que notre mutation n'entraîne pas un code équivalent _(ex: >= et = peuvent être équivalents dans certains cas)._ Si on ne peut pas déterminer la pertinence d'un test pour notre mutant, il est très probable que le test soit au vert tout simplement car le test en question n'a pas de rapport avec la modification. Ainsi il sera impossible de déterminer la qualité du test car un test au vert pourra signifier trois choses, soit le test a laissé passé le mutant :
1. à cause d'un manque de qualité du test.
2. car la mutation de ce dernier n'a aucun lien avec le test.
3. car la mutation produit du code équivalent.

Il nous paraît donc impératif de remplir ces conditions pour pouvoir proposer des résultats avec un minimum de valeur.

Nous obtenons suite à cette phase de test un fichier XML par mutant. Notre script bash va alors appeler un programme python qui écrira les informations souhaitées provenant de plusieurs fichiers XML dans un seul fichier HTML. Dans un soucis de présentation on utilisera les frameworks Bootstrap et HightChart.

## Mutations
### Mutations disponibles

Nous avons étudié lors du cours de compilation les arbres synthaxiques produits par une compilation. L'idée est donc de commencer par modifier les feuilles de ces arbres car ce sont les modifications qui doivent avoir le moins d'incidence sur notre programme. Une fois les mutations faites au niveau des feuilles, nous pouvons remonter dans l'arbre syntaxique et exécuter des mutations à un niveau plus haut. Plus nous ferons des mutations qui concernent des morceaux de code haut dans l'arbre, plus nous aurons des chances d'échouer à la compilation, ce qui doit être évité car si notre mutant ne peut pas être exécuté, il ne pourra pas mettre à l'épreuve les tests. 

Il nous a fallu aussi considérer la complexité d'implémenter nos propres mutations, nous avons donc choisi des mutations ''classiques'', c'est-à-dire des mutations que nous avons pu voir sur plusieurs sites. Cela pour augmenter nos chances de pouvoir trouver des codes existants et nous garantir de les implémenter dans les délais.

Voici six mutations différentes qui peuvent être appliquées au code:
*  Modification des comparateurs logiques _(ex: > sera transformé en >=)_
*  Modification au niveau des boucles _(ex: changement du nombre d'itérations)_
*  Ajout d'opérateur unaire à toutes les valeurs numériques _(ex: si on trouve un int i dans le code, on le transforme en ++i ou en -i,...)_
*  Mise à null des variables lors de leur déclaration
*  Remplacement des constantes entières _(ex: soit par 0 soit par le nombre maximum)_
*  Suppression d'instruction(s)

### Où les appliquer et comment
Notre objectif est de tester tout le banc de test. Pour se faire nous pensons qu'il ne faut pas négliger des parties du code. Nous pensions faire une mutation dès que cela est possible, et en faire un mutant. Ainsi nous souhaitions parcourir le code séquentiellement, repartir de la dernière mutation si c'est le cas, faire une unique mutation et en faire un mutant. Cette méthode ne paraît pas raisonnable et nous avons considéré le fait de pouvoir appliquer les mutations avec une certaine probabilité. En créant un nombre de mutant en adéquation avec la probabilité de faire une mutation, on peut maximiser les chances de faire des mutations sur l'ensemble du code source. Pour que cette stratégie soit valable il faut considérer le problème suivant : nos probabilités de mutation ne vont pas avoir le même impact suivant la taille du code. Si cette probabilité est trop grande et que le code l'est aussi, nous allons faire trop de mutation pour un seul mutant, tandis que si le code est petit pour une probabilité faible nous risquons de n'avoir aucune mutation suite à notre processus. Notre solution est de compter le nombre de lignes de notre code source et de fixer ensuite une probabilité et le nombre de mutant à faire pour une mutation possible en relation avec ce nombre de lignes.