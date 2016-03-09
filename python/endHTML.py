#! /usr/bin/env python3.4

import sys
import os
from os import listdir
from os.path import isfile, join



html = open('./python/report/rapMutant.html', 'a')

cRead = open('./python/count.txt', 'r')
for lineC in cRead :
	double = lineC.split('.')
	d = double[0] # nombre de mutant fail
	t = double[1] # nombre de mutant error 
	a = double[2] # nombre de mutant vivant
	n = double[3] # nombre de mutant mort-né

html.write('\n\t\t</div><input id="fail" style="display: none" value="' + d + '">\n')
html.write('\t\t<input id="error" style="display: none" value="' + t + '">\n')
html.write('\t\t<input id="success" style="display: none" value="' + a + '">\n')
html.write('\t\t<input id="stillborn" style="display: none" value="' + n + '">\n')
html.write('\t\t<div id="container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>\n')

html.write('\t\t<img src="css/images/deadpool.jpg" width="150" height="100"class="img-responsive img-circle deadpoolLeft">\n')
html.write('\t\t<img src="css/images/deadpool.jpg" width="150" height="100"class="img-responsive img-circle deadpoolRight">\n')
html.write('\t\t<script src="https://code.highcharts.com/highcharts.js"></script>\n')
html.write('\t\t<script src="https://code.highcharts.com/modules/exporting.js"></script>\n')
html.write('\t\t<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>\n')
html.write('\t\t<script src="js/bootstrap.min.js"></script>\n')
html.write('\t\t<script src="js/donut.js"></script>\n')
html.write('\t</body>\n')
html.write('</html>')