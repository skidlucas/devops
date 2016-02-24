#! /usr/bin/env python3.4
import sys

xml = open('./transformation-code/target/surefire-reports/TEST-AppTest.xml', 'r')

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