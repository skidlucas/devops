#! /usr/bin/env python3.4

import sys
import os
from os import listdir
from os.path import isfile, join

data = open('./python/report/data.txt', 'a')

testpath = './transformation-code/src/test/java/'
testfiles = [f for f in listdir(testpath) if isfile(join(testpath, f))]

state = 1 # etat du mutant

data.write('proc ' + sys.argv[1] + '\n')
for elt in testfiles:
	test = os.path.splitext(elt)[0]
	testreport = './transformation-code/target/surefire-reports/TEST-' + test + '.xml'
	#print('for')
	try:
		xml = open(testreport, 'r')
		for line in xml:
			clashSpace = line.strip()
			split = clashSpace.split(' ')
			if split[0] == '<testcase' :
				res = ' '.join(split).strip('></')
				data.write(res + '\n')
				# mon mutant peut survivre
				state = 1
				#print('testcase')
			if split[0] == '<failure' :
				res = line.split('>')
				data.write(res[1])
				# mon mutant a été tué par un test
				state = 0
				#print('failure')
			if split[0] == '<error' :
				res = line.split('>')
				data.write(res[1])
				# mon mutant a fait echouer un test
				state = -1
				#print('error')
	except IOError:
		print('ERROR ' + testreport + " doesn't exist")
	#print('fin for')


	# on update le count.txt
	cRead = open('./python/count.txt', 'r')
	for lineC in cRead :
		triple = lineC.split('.')
		d = triple[0] # le premier int correspond au nombre de mutant mort
		t = triple[1] # le deuxieme int correspond au nombre de mutant faisant echouer les tests (donc mort)
		a = triple[2] # le troisième int correspond au nombre de mutant mort
	cRead.close()

	cWrite = open('./python/count.txt', 'w')
	if (state == 1) : #vivant
		newD = int(d)
		newT = int(t)
		newA = int(a) + 1
	elif (state == 0) : #fail
		newD = int(d) + 1
		newT = int(t)
		newA = int(a)
	elif (state == -1) : #error
		newD = int(d)
		newT = int(t) + 1
		newA = int(a)

	cWrite.write(str(newD) + '.' + str(newT) + '.' + str(newA))
	cWrite.close()