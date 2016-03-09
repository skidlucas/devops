#! /usr/bin/env python3.4

import sys
import os
from os import listdir
from os.path import isfile, join

html = open('./python/report/rapMutant.html', 'w')
html.write('<!DOCTYPE html>\n')
html.write('<html>\n')
html.write('\t<head>\n')
html.write('\t\t<meta charset="UTF-8">\n')
html.write('\t\t<meta http-equiv="X-UA-Compatible" content="IE=edge">\n')
html.write('\t\t<meta name="viewport" content="width=device-width, initial-scale=1">\n')
html.write('\t\t<title>Rapport sur la guerre des mutants</title>\n')
html.write('\t\t<link href="css/bootstrap.min.css" rel="stylesheet">\n')
html.write('\t\t<link href="css/style.css" rel="stylesheet">\n')
html.write('\t</head>\n')
html.write('\t<body>\n')
html.write('\t\t<img src="css/images/deadpool.jpg" width="150" height="100"class="img-responsive img-circle deadpoolLeft">\n')
html.write('\t\t<img src="css/images/deadpool.jpg" width="150" height="100"class="img-responsive img-circle deadpoolRight">\n')
html.write('\t\t<h1>Rapport sur la guerre des mutants</h1>\n')
html.write('\t\t<h3>réalisé par Lucas Martinez, Simon Paris et Lucas Soumille</h3><br />\n')

#init count.txt
c = open('./python/count.txt', 'w')
c.write('0.0.0.0')
c.close()

#clean data.txt
open('./python/report/data.txt', 'w').close()
