$(function () {

    var success = document.getElementById("success").value;
    var fail = document.getElementById("fail").value;
    var error = document.getElementById("error").value;

    var totalDead = parseInt(fail) + parseInt(error);
    //ajouter les morts nés après

    var colors = Highcharts.getOptions().colors,
        categories = ['Mutants morts', 'Mutants vivants'],
        data = [{
            y: totalDead,
            color: colors[8],
            drilldown: {
                name: 'Cause',
                categories: ['Erreur', 'Test fail', 'Mort né'],
                data: [parseInt(error), parseInt(fail), 0],
                color: colors[8]
            }
        }, {
            y: parseInt(success),
            color: colors[2],
            drilldown: {
                categories: ['Mutants vivants'],
                data: [parseInt(success)],
                color: colors[2]
            }
        }],
        mutantData = [],
        reasonData = [],
        i,
        j,
        dataLen = data.length,
        drillDataLen,
        brightness;


    // Build the data arrays
    for (i = 0; i < dataLen; i += 1) {

        // add browser data
        mutantData.push({
            name: categories[i],
            y: data[i].y,
            color: data[i].color
        });

        // add version data
        drillDataLen = data[i].drilldown.data.length;
        for (j = 0; j < drillDataLen; j += 1) {
            brightness = 0.2 - (j / drillDataLen) / 5;
            reasonData.push({
                name: data[i].drilldown.categories[j],
                y: data[i].drilldown.data[j],
                color: Highcharts.Color(data[i].color).brighten(brightness).get()
            });
        }
    }

    // Create the chart
    var chart = new Highcharts.Chart({
        chart: {
            type: 'pie',
            renderTo: 'container'
        },
        title: {
            text: 'Guerre des mutants'
        },
        yAxis: {
            title: {
                text: ''
            }
        },
        plotOptions: {
            pie: {
                shadow: false,
                center: ['50%', '50%']
            }
        },
        tooltip: {
            valueSuffix: ''
        },
        series: [{
            name: 'Nombre',
            data: mutantData,
            size: '60%',
            dataLabels: {
                formatter: function () {
                    return this.point.name; //aff
                },
                color: '#ffffff',
                distance: -30
            }
        }, {
            name: 'Nombre',
            data: reasonData,
            size: '80%',
            innerSize: '60%',
            dataLabels: {
                formatter: function () {
                    // display only if larger than 1
                    return this.y > 0 ? '<b>' + this.point.name + ':</b> ' + this.y : null;
                }
            }
        }]
    });
});