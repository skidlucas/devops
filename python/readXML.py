#! /usr/bin/env python3.4

import sys
import os
from os import listdir
from os.path import isfile, join

html = open('./python/rapMutant.html', 'w')
html.write('<!DOCTYPE html>\n')
html.write('<html>\n')
html.write('<head>\n')
html.write('<title>Rapport sur la guerre des mutants</title>\n')
html.write('</head>\n')
html.write('<body>\n')
html.write('<h1>Rapport sur la guerre des mutants</h1>\n')
html.write('<p> Avec le processeur : ')
html.write(sys.argv[1])
html.write('</p>')
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
			html.write('<p>')
			html.write(res)
			html.write('</p>')
			html.write('\n')
		if split[0] == '<failure' :
			msg_failure = line.split('>')
			html.write('<p>')
			html.write(msg_failure[1])
			html.write('</p>')
			html.write('\n')


html.write('</body>')
html.write('</html>')