var myMap;

window.onload = function () {
    myMap = echarts.init(document.getElementById('mapgis'));
    var url = "/fop/www/companyGis";
    $.getJSON(url, function (result) {
        option.series[0].data = JSON.parse(result.data);
        myMap.setOption(option);
    });
    loadMap();

    myMap.on('click', function (params) {
        loadMap(params.name);
    });
}

var option = {
    backgroundColor: '#fff',
    title: {
        text: '中国地区',
        subtext: '企业会员分布图',
        x: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: function (params) {
            return params.name/* + ' : ' + params.value[2]*/;
        }
    },
    legend: {
        orient: 'vertical',
        y: 'bottom',
        x: 'right',
        data: ['会员企业'],
        textStyle: {
            color: '#000'
        }
    },
    geo: {
        map: '中国',
        //roam: true,
        label: {
            normal: {
                show: false,
                fontSize: 10,
            },
            emphasis: {
                show: true,
                fontSize: 12,
                areaColor: '#ADC2DE',
            }
        },
        itemStyle: {
            normal: {
                areaColor: '#fff',
                borderColor: '#1A56A8'
            },
            emphasis: {
                areaColor: '#ADC2DE',
            }

        }
    },
    series: [{
        name: '会员企业',
        type: 'scatter',
        coordinateSystem: 'geo',
        data: [],
        symbolSize: 12,
        symbol: 'pin',
        label: {
            normal: {
                show: false
            },
            emphasis: {
                show: false
            }
        },
        itemStyle: {
            emphasis: {
                borderColor: '#fff',
                borderWidth: 1
            }
        }
    }
    ]
};

function loadMap(cityName) {
    var cityCode = cityMap[cityName];
    if (!cityCode) {
        cityName = '中国';
        cityCode = 'china';
    }
    $.get('china-main-city/' + cityCode + '.json', function (geoJson) {
        // option.geo.roam = false;
        // myMap.setOption(option);
        echarts.registerMap(cityName, geoJson);
        option.geo.map = cityName;
        option.title.text = cityName + "地区";
        myMap.setOption(option);
    });
}