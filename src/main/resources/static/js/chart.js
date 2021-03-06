var passats = [];
google.charts.load('current', {'packages':['corechart']});
function drawChart() {
    var p = [];
    p[0] = ['Mileage', 'Price'];

    for (var i = 0; i < passats.length; i++) {
        p[i + 1] = [passats[i].mileage, passats[i].price];
    }
    var data = google.visualization.arrayToDataTable(p);

    var options = {
        title: '',
        hAxis: {title: 'Mileage', titleTextStyle: {color: '#333'}},
        vAxis: {minValue: 0}
    };

    var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
    chart.draw(data, options);
}