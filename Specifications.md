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

### Description de notre chaîne de build
Nous allons utiliser un script bash pour automatiser nos différentes étapes. La première sera assumée par Maven.

Nous allons nous servir d'une partie des phases de Maven, plus précisement des phases de build suivantes :
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

```sh
$ ./mutatingtest.sh
```