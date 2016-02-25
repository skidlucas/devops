#! /usr/bin/env python3.4

import sys
import os
from os import listdir
from os.path import isfile, join

html = open('./python/report/rapMutant.html', 'a')

testpath = './transformation-code/src/test/java/'
testfiles = [f for f in listdir(testpath) if isfile(join(testpath, f))]

html.write('\t\t<br /><p> Processeur utilisé: ')
html.write('<b>' + sys.argv[1] + '</b>')
html.write('</p>\n')

alive = 0 #boolean pour savoir si mon mutant est vivant
for elt in testfiles:
	test = os.path.splitext(elt)[0]
	testreport = './transformation-code/target/surefire-reports/TEST-' + test + '.xml'

	xml = open(testreport, 'r')
	for line in xml:
		clashSpace = line.strip()
		split = clashSpace.split(' ')
		if split[0] == '<testcase' :
			res = ' '.join(split).strip('></')
			html.write('\t\t<p>')
			html.write(res)
			html.write('</p>')
			html.write('\n')
			# mon mutant peut survivre
			alive = 1
		if split[0] == '<failure' :
			msg_failure = line.split('>')
			html.write('\t\t<p>')
			html.write(msg_failure[1])
			html.write('</p>')
			html.write('\n')
			alive = 0

	# on update le count.txt
	cRead = open('./python/count.txt', 'r')
	for lineC in cRead :
		double = lineC.split('.')
		d = double[0] # le premier int correspond au nombre de mutant mort
		a = double[1] # le deuxieme int correspond au nombre de mutant mort
	#si alive = 1 alors le mutant a survecu
	cWrite = open('./python/count.txt', 'w')
	if (alive) :
		newD = int(d)
		newA = int(a) + 1
	else :
		newD = int(d) + 1
		newA = int(a)
	cWrite.write(str(newD) + '.' + str(newA))
	cRead.close()
	cWrite.close()