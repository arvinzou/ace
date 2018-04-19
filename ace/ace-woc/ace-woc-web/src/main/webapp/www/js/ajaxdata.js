window.setInterval(function () {
    updata();
}, 10000)

function updata() {
    var url = '/www/data/statistics';
    var data = {};
    $.getJSON(url, data, function (result) {
        viewNumber('.trafficCounts', result.trafficCounts);
        viewNumber('.trafficIllegalCounts', result.trafficIllegalCounts);
        var numbers = toDecimal((result.trafficIllegalCounts / result.trafficCounts) * 100);
        optionGauge.series[0].data[0].value = numbers;
        myGauge.setOption(optionGauge, true);
    });

    var url = '/woc/www/data/site';
    data = {}
    $.getJSON(url, data, function (result) {
        if (result.siteList.length) {
            var siteBar = [];
            var dataBar = [];
            var data = result.siteList;
            for (var i = 0; i < data.length; i++) {
                siteBar.push(data[i].siteCode);
                dataBar.push(data[i].count);
            }
            ;
            optionBar.xAxis[0].data = siteBar;
            optionBar.series[0].data = dataBar;
            myBar.setOption(optionBar);
        }
    });

    var url = '/www/data/interval';
    data = {}
    $.getJSON(url, data, function (result) {
        var dataLine = [];
        var data = result.siteList;
        for (var i = 0; i < hours; i++) {
            dataLine.push(result.countMap[result.interval[i]]);
        }
        ;
        optionLine.series[0].data = dataLine;
        myLine.setOption(optionLine);
    });
}
