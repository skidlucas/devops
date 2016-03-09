#! /usr/bin/env python3.4

import sys
import os

dataTXT = open('./python/report/data.txt', 'r')
html = open('./python/report/rapMutant.html', 'a')
html.write('<p id="description">Vous pouvez cliquer sur les processeurs afin d\'obtenir en détails les résultats obtenus en effectuant un <i>diff</i> entre les fichiers sources et les fichiers mutés.</p>')
for line in dataTXT:
	split = line.split('_')
	if split[0] == 'proc' :
		html.write('\t\t<br />\n\t\t</div><button data-toggle="collapse" data-target="#'+split[1].strip()+'"')
		html.write(' class="btn btn-lg btn-default processor">')
		html.write(split[1].strip())
		processor = split[1].strip()
		#html.write('</div>\n')
		#html.write('\t\t<div class="resultsPerProc">\n')
	elif split[0] == 'selec' :
		html.write(' (' + split[1].strip() + '%)  ')
		html.write('<span class="glyphicon glyphicon-menu-down"></span></button>\n')
		html.write('<div id="'+processor+'" class="container collapse">\n')
	elif split[0] == 'DIFF' :
		html.write('<br /><a href="#myModal' +split[1].strip()+'_'+split[2].strip())
		html.write('" data-toggle="modal" data-target="#myModal' +split[1].strip()+'_'+split[2].strip() +'">')
		html.write(split[2].strip() + '</a>\n')
		html.write('<div class="modal fade" id="myModal' +split[1].strip()+'_'+split[2].strip()+'" role="dialog">')
		html.write('<div class="modal-dialog"> <div class="modal-content"> <div class="modal-header">')
		html.write('<button type="button" class="close" data-dismiss="modal">&times;</button>')
		html.write('<h4 class="modal-title">'+split[1].strip()+': '+split[2].strip()+'</h4>')
		html.write('</div><div class="modal-body">')
		html.write('<embed src="diff/DIFF_'+split[1].strip()+'_'+split[2].strip()+'.txt" width="500" height="300"/></div>')
		html.write('<div class="modal-footer">')
		html.write('<button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>')
		html.write('</div></div></div></div>')		
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