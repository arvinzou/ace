$(function () {
    // var url = contextPath + '/www/data/statistics';
    // var data = {};
    // $.getJSON(url, data, function(result) {
    //     viewNumber('.trafficCounts', result.trafficCounts);
    //     viewNumber('.trafficIllegalCounts', result.trafficIllegalCounts);
    //     var numbers
    //     if(result.trafficCounts==0||result.trafficIllegalCounts==0){
    //         numbers=0;
    //     }else{
    //         numbers = toDecimal((result.trafficIllegalCounts / result.trafficCounts) * 100);
    //     }
    //     optionGauge.series[0].data[0].value = numbers;
    //     myGauge.setOption(optionGauge, true);
    // });

    var url = contextPath + '/www/data/site';
    data = {}
    $.getJSON(url, data, function (result) {
        if (result.siteList.length) {
            var siteBar = [];
            var dataBar = [];
            var data = result.siteList;
            for (var i = 0; i < data.length; i++) {
                siteBar.push(data[i].siteName);
                dataBar.push(data[i].count);
            }
            ;
            console.log(siteBar);
            console.log(dataBar);
            option3.xAxis[0].data = siteBar;
            option3.series[0].data = dataBar;
            ct3.setOption(option3);
        }
    });

    var url = contextPath + '/www/data/interval';
    data = {}
    $.getJSON(url, data, function (result) {
        var dataLine = [];
        for (var i = 0; i < getHours() + 1; i++) {
            dataLine.push(result.countMap[result.interval[i]]);
        }
        ;
        option1.series[0].data = dataLine;
        ct1.setOption(option1);
    });
});

function getHours() {
    var date = new Date();
    return date.getHours();
}