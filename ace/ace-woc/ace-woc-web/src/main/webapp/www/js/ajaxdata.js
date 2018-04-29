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
                dataBar.push(data[i].illegalCount);
                if (data[i].status == 1) {
                    dataMap.push({
                        symbol: 'pin',
                        name: data[i].siteName,
                        trafficCount: data[i].trafficCount,
                        illegalCount: data[i].illegalCount,
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
                        trafficCount: data[i].trafficCount,
                        illegalCount: data[i].illegalCount,
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
        optionLine.series[0].data = dataLine;
        myLine.setOption(optionLine);
    });
}

function showTooltip(geoData, trafficData) {
    optionMap.series[1].data = geoData;
    myMap.setOption(optionMap);
}

function websocket() {
    if ("WebSocket" in window) {
        var ws = new WebSocket("ws://127.0.0.1:6006/woc/websocket/D9F7E76732A24488BCCD652B7EEBFD09/E341A7084C56499AB0390BCAA3AA5BCD");
        ws.onopen = function (evt) {
            console.log("链接成功");
            console.log(evt);
        };

        ws.onmessage = function (evt) {
            console.log(evt);
            var received_msg = evt.data;
            getTraffic(tid);
        };

        ws.onclose = function (evt) {
            console.log("链接关闭");
            console.log(evt);
        };

        ws.onerror = function (evt) {
            console.log('链接错误');
            console.log(evt)
        }

    } else {

        alert("您的浏览器不支持 WebSocket!");
    }
}

function getTraffic(tid) {
    var url = 'http://localhost/woc/www/data/getTrafficByKey';
    data = {
        id: tid
    }
    $.getJSON(url, data, function (result) {
        console.log(result);
        if (result.status == 0) {
            hideTooltip();
            clearTimeout(timer1);
            activeTooltip(result.value);
        }
    });
}

function activeTooltip(data) {
    $.each(data, function (key, value) {
        if (key == 'siteId') {
            activelocal(value);
        }
        $('#tp' + key).text(value);
    });
    $('#targetDiv').addClass('move');
    $('#embg')[0].play();
    time3second();
}


function activelocal(siteId) {
    var gisData = optionMap.series[0].markPoint.data;
    for (var i = 0; i < gisData.length; i++) {
        if (gisData[i].siteId == siteId) {
            optionMap.series[1].data = [];
            optionMap.series[1].data.push({value: gisData[i].coord});
            myMap.setOption(optionMap);
            return;
        }
    }
}

var timer1;

function time3second() {
    timer1 = setTimeout(hideTooltip, 3000);
}

function hideTooltip() {
    optionMap.series[1].data = [];
    myMap.setOption(optionMap);
    $('#targetDiv').removeClass('move');
}
