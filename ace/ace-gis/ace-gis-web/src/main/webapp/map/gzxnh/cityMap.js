var cityMap = {
    "贵阳市": "520100",
    "六盘水市": "520200",
    "遵义市": "520300",
    "安顺市": "520400",
    "毕节市": "520500",
    "黔西南布依族苗族自治州": "522300",
    "黔东南苗族侗族自治州": "522600",
    "黔南布依族苗族自治州": "522700" 
};
var curIndx = 0;
var mapType = [];
var mapGeoData={};
mapGeoData.params = require('../js/echarts/util/mapData/params');
for (var city in cityMap) {
	console.log(city);
    mapType.push(city);
    // 自定义扩展图表类型
    mapGeoData.params[city] = {
        getGeoJson: (function (c) {
            var geoJsonName = cityMap[c];
            return function (callback) {
                $.getJSON('http://127.0.0.1:7001/map/js/echarts/util/mapData/geoJson/china-main-city/' + geoJsonName + '.json', callback);
            }
        })(city)
    }
}

var ecConfig =_config;// require('../js/echarts/config');
var zrEvent =_EventRiver;// require('../js/zrender/tool/event');