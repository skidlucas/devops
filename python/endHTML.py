#! /usr/bin/env python3.4

import sys
import os
from os import listdir
from os.path import isfile, join



html = open('./python/report/rapMutant.html', 'a')

cRead = open('./python/count.txt', 'r')
for lineC in cRead :
	double = lineC.split('.')
	d = double[0] # le premier int correspond au nombre de mutant mort
	a = double[1] # le deuxieme int correspond au nombre de mutant mort

#		<input id="fail" style="display: none" value="45">
#		<input id="success" style="display: none" value="5">
#		<div id="container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>

html.write('\t\t<input id="fail" style="display: none" value="' + d + '">\n')
html.write('\t\t<input id="success" style="display: none" value="' + a + '">\n')
html.write('\t\t<div id="container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>\n')


html.write('\t\t<script src="https://code.highcharts.com/highcharts.js"></script>\n')
html.write('\t\t<script src="https://code.highcharts.com/modules/exporting.js"></script>\n')
html.write('\t\t<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>\n')
html.write('\t\t<script src="js/bootstrap.min.js"></script>\n')
html.write('\t\t<script src="js/pie.js"></script>\n')
html.write('\t</body>\n')
html.write('</html>')