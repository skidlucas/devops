#! /bin/bash

echo "Framework de Mutation Testing réalisé par Lucas Martinez, Simon Paris et Lucas Soumille"

cd spoon-maven-plugin-examples-master/
mvn clean test
../python/readXML.py
firefox ../python/rapMutant.html