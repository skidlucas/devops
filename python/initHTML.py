#! /usr/bin/env python3.4
# attention au close

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
html.write('\t\t<h1>Rapport sur la guerre des mutants</h1>\n')

html.write('\t\t<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>\n')
html.write('\t\t<script src="js/bootstrap.min.js"></script\n')
html.write('\t</body>\n')
html.write('</html>')