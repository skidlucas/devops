# Spécifications du projet

Pour réaliser l'intégralité du projet, nous allons utiliser les outils suivants :
* Maven
* Spoon
* JUnit
* Bootstrap
* HighChart
* Java
* Bash
* XSL
* Python

### Description de la chaîne de build

Nous allons nous servir de Maven, plus précisement des phases de build suivantes :
* validate
* compile
* test

Nous allons modifier uniquement les phases _compile_ et _test_. Ainsi nous pourrons répondre à notre problématique tout en profitant de l'automatisation que nous offre Maven.  
Dans la phase _compile_, nous allons modifier en premier lieu la sous-phase _generate-sources_ pour insérer l'outil **Spoon**, en effet, c'est à ce niveau-là que nous avons besoin de générer des mutants. Par la suite, il nous faudra spécifier à Maven que c'est les mutants qu'il doit compiler et non le code source qu'il nous ait fourni. Il est possible qu'il y ait d'autres sous-phases à modifier pour arriver à nos fins.

Concernant la phase _test_, il faut qu'on puisse exclure des tests en fonction du mutant à tester pour pouvoir proposer une stratégie valable d'un point de vue performance. En effet, il paraît déraisonnable d'appliquer à chaque mutant l'ensemble du banc de test. L'idéal serait de pouvoir déterminer, pour un mutant donné, les tests qui concernent le code qui a été muté. Une fois ces tests lancés, nous pourrons ainsi dire avec certitude que les tests au vert sont de mauvaise qualité, sous réserve que notre mutation n'entraîne pas un code équivalent. (_<u>ex:</u> >= et = peuvent être équivalents dans certains cas_)


```sh
$ mvn test
```