$(function () {

    $(document).ready(function () {

        var fail = document.getElementById("fail").value;
        var success = document.getElementById("success").value;       

        // Build the chart
        var chart = new Highcharts.Chart({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie',
                renderTo: 'container'
            },
            title: {
                text: 'Guerre des mutants'
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.y}</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
                }
            },
            series: [{
                name: 'Nombre',
                colorByPoint: true,
                data: [{
                        "name": 'Mutants tués',
                        "y": parseInt(fail),
                        "color": 'red'
                    }, {
                        "name": 'Mutants vivants',
                        "y": parseInt(success),
                        "color": 'black',
                        "sliced": true,
                        "selected": true
                    }]
            }]
        });
    });
});