#! /bin/bash

echo "Framework de Mutation Testing réalisé par Lucas Martinez, Simon Paris et Lucas Soumille"

mvn clean test
./python/readXML.py
xdg-open ./python/rapMutant.html &
