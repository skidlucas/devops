#! /usr/bin/env python3.4

import sys
import os
from os import listdir
from os.path import isfile, join



html = open('./python/report/rapMutant.html', 'a')

		<input id="total" style="display: none" value="50">
		<input id="fail" style="display: none" value="45">
		<input id="success" style="display: none" value="5">
		<div id="container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>


html.write('\t\t<script src="https://code.highcharts.com/highcharts.js"></script>\n')
html.write('\t\t<script src="https://code.highcharts.com/modules/exporting.js"></script>\n')
html.write('\t\t<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>\n')
html.write('\t\t<script src="js/bootstrap.min.js"></script>\n')
html.write('\t\t<script src="js/pie.js"></script>\n')
html.write('\t</body>\n')
html.write('</html>')