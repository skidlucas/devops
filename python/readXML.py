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
html.write('\t\t<h1>Rapport sur la guerre des mutants</h1>\n')
html.write('\t\t<p> Processeur utilisé: ')
html.write('<b>' + sys.argv[1] + '</b>')
html.write('</p>\n')

testpath = './transformation-code/src/test/java/'

testfiles = [f for f in listdir(testpath) if isfile(join(testpath, f))]

for elt in testfiles:
	test = os.path.splitext(elt)[0]
	testreport = './transformation-code/target/surefire-reports/TEST-' + test + '.xml'

	xml = open(testreport, 'r')
	for line in xml:
		clashSpace = line.strip()
		split = clashSpace.split(' ')
		if split[0] == '<testcase' :
			res = ' '.join(split).strip('></')
			html.write('\t\t\t<p>')
			html.write(res)
			html.write('</p>')
			html.write('\n')
		if split[0] == '<failure' :
			msg_failure = line.split('>')
			html.write('\t\t\t<p>')
			html.write(msg_failure[1])
			html.write('</p>')
			html.write('\n')

html.write('\t\t<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>\n')
html.write('\t\t<script src="js/bootstrap.min.js"></script\n')
html.write('\t</body>\n')
html.write('</html>')