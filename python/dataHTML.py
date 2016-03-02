#! /usr/bin/env python3.4

import sys
import os

dataTXT = open('./python/report/data.txt', 'r')
html = open('./python/report/rapMutant.html', 'a')
for line in dataTXT:
	split = line.split(' ')
	if split[0] == 'proc' :
		html.write('\t\t<br />\n\t\t<div class="processor">Processeur utilisé: ' )
		html.write('<span class="processorName">' + split[1] + '</span>')
		html.write('</div>\n')
		html.write('\t\t<div class="resultsPerProc">\n')
	elif split[0] == 'testcase' :
		html.write('\t\t\t<div>')
		html.write(line)
		html.write('</div>')
		html.write('\n')
	else :
		split = line.split('.')
		if split[0] == 'junit' :
			html.write('\t\t\t<div class="fail">')
			html.write(line)
			html.write('</div>')
			html.write('\n')
		elif split[0] == 'java' :
			html.write('\t\t\t<div class="error">')
			html.write(line)
			html.write('</div>')
			html.write('\n')
dataTXT.close()
html.close()