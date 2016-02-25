#! /usr/bin/env python3.4

import sys
import os
from os import listdir
from os.path import isfile, join

html = open('./python/report/rapMutant.html', 'a')

testpath = './transformation-code/src/test/java/'
testfiles = [f for f in listdir(testpath) if isfile(join(testpath, f))]

html.write('\t\t<p> Processeur utilisé: ')
html.write('<b>' + sys.argv[1] + '</b>')
html.write('</p>\n')

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