#! /usr/bin/env python3.4

xml = open('TEST-MainTest.xml', 'r')

html = open('rapMutant.html', 'w')
html.write('<!DOCTYPE html>\n')
html.write('<html>\n')
html.write('<head>\n')
html.write('<title>rapport sur la guerre des mutants</title>\n')
html.write('</head>\n')
html.write('<body>\n')
html.write('<h1>rapport sur la guerre des mutants</h1>\n')


for line in xml:
	clashSpace = line.strip()
	split = clashSpace.split(' ')
	if split[0] == '<testcase' :
		res = ' '.join(split).strip('></')
		html.write('<p>')
		html.write(res)
		html.write('</p>')
		html.write('\n')


html.write('</body>')
html.write('</html>')