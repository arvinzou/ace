window.setInterval(function () {
    updata();
}, 10000)

function updata() {
    var url = 'http://106.75.69.81/woc/www/data/statistics';
    var data = {};
    $.getJSON(url, data, function (result) {
        viewNumber('.trafficCounts', result.trafficCounts);
        viewNumber('.trafficIllegalCounts', result.trafficIllegalCounts);
        var numbers
        if (result.trafficCounts == 0 || result.trafficIllegalCounts == 0) {
            numbers = 0;
        } else {
            numbers = toDecimal((result.trafficIllegalCounts / result.trafficCounts) * 100);
        }
        optionGauge.series[0].data[0].value = numbers;
        myGauge.setOption(optionGauge, true);
    });

    var url = 'http://106.75.69.81/woc/www/data/site';
    data = {
        startDt: utilFormatDataTime(new Date(), 'date') + ' 00:00:00',
        endDt: utilFormatDataTime(new Date(), 'datetime'),
    }
    $.getJSON(url, data, function (result) {
        if (result.siteList.length) {
            var siteBar = [];
            var dataBar = [];
            var dataMap = [];
            var data = result.siteList;
            for (var i = 0; i < data.length; i++) {
                siteBar.push(data[i].siteCode);
                dataBar.push(data[i].count);
                if (data[i].status == 1) {
                    dataMap.push({
                        symbol: 'pin',
                        name: data[i].siteName,
                        count: data[i].count,
                        siteId: data[i].id,
                        coord: [data[i].longitude, data[i].latitude],
                        tooltip: { // Series config.
                            trigger: 'item',
                            position: [90, 380],
                        },
                    });
                } else if (data[i].status == 2) {
                    dataMap.push({
                        symbol: 'triangle',
                        symbolSize: 15,
                        name: data[i].siteName,
                        coord: [data[i].longitude, data[i].latitude],
                        count: data[i].count,
                        siteId: data[i].id,
                        tooltip: { // Series config.
                            trigger: 'item',
                            position: [90, 380],
                        },
                        itemStyle: {
                            normal: {
                                color: 'red',
                                label: {
                                    show: false
                                },
                            },
                            emphasis: {
                                color: '#ff0',
                                label: {
                                    show: false
                                },
                            }
                        },
                    });
                }
                ;
            }
            optionBar.xAxis[0].data = siteBar;
            optionBar.series[0].data = dataBar;
            optionMap.series[0].markPoint.data = dataMap;
            myBar.setOption(optionBar);
            myMap.setOption(optionMap);
        }

    });

    var url = 'http://106.75.69.81/woc/www/data/interval';
    data = {}
    $.getJSON(url, data, function (result) {
        var dataLine = [];
        for (var i = 0; i < hours + 1; i++) {
            dataLine.push(result.countMap[result.interval[i]]);
        }
        ;
        optionLine.series[0].data = dataLine;
        myLine.setOption(optionLine);
    });
}

function showTooltip(geoData, trafficData) {
    optionMap.series[1].data = geoData;
    myMap.setOption(optionMap);
}