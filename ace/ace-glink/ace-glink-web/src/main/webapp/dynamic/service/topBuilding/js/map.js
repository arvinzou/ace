var longitude = null;
var latitude = null;
$(function () {
    var locaUrl = window.location.href;
    var url = window.location.href.substring(locaUrl.indexOf("?") + 1);
    var paramArr = url.split("&");
    for (var i = 0; i < paramArr.length; i++) {
        num = paramArr[i].indexOf("=");
        if (num > 0) {
            name = paramArr[i].substring(0, num);
            value = paramArr[i].substr(num + 1);
            if (name == "longitude") {
                longitude = value;
            }
            if (name == "latitude") {
                latitude = value;
            }
        }
    }
    if (longitude) {

        //坐标转换
        var location = MapConvert.Convert_GCJ02_To_BD09({lat: latitude, lng: longitude});
        console.log("lat:" + latitude + ",lng:" + longitude);
        console.log(JSON.stringify(location));
        //初始化地图
        // 限定缩放比例，15-17
        var map = new BMap.Map(container);
        // 创建点坐标 -- 建筑物位置
        var point = new BMap.Point(location.lng, location.lat);
        // 初始化地图，设置中心点坐标和地图级别
        map.centerAndZoom(point, 17);

        //地图描点功能
        // 设置标示
        var marker = new BMap.Marker(point);//, {icon: icon}
        map.addOverlay(marker);

    }
});
