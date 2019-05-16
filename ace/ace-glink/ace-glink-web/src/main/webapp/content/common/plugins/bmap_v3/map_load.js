var bmapcfg = {
    'imgext': '.png',   //瓦片图的后缀 ------ 根据需要修改，一般是 .png .jpg
    'tiles_dir': '',       //普通瓦片图的地址，为空默认在 offlinemap/tiles/ 目录
    'tiles_hybrid': '',       //卫星瓦片图的地址，为空默认在 offlinemap/tiles_hybrid/ 目录
    'tiles_self': ''        //自定义图层的地址，为空默认在 offlinemap/tiles_self/ 目录
};

var MapConvert = {
    x_pi: 3.14159265358979324 * 3000.0 / 180.0,
    /// <summary>
    /// 中国正常坐标系GCJ02协议的坐标，转到 百度地图对应的 BD09 协议坐标
    ///  point 为传入的对象，例如{lat:xxxxx,lng:xxxxx}
    /// </summary>
    Convert_GCJ02_To_BD09: function (point) {
        var x = point.lng, y = point.lat;
        var z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * MapConvert.x_pi);
        var theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * MapConvert.x_pi);
        point.lng = z * Math.cos(theta) + 0.0065;
        point.lat = z * Math.sin(theta) + 0.006;

        return point;
    },
    /// <summary>
    /// 百度地图对应的 BD09 协议坐标，转到 中国正常坐标系GCJ02协议的坐标
    /// </summary>
    Convert_BD09_To_GCJ02: function (point) {
        var x = point.lng - 0.0065, y = point.lat - 0.006;
        var z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * MapConvert.x_pi);
        var theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * MapConvert.x_pi);
        point.lng = z * Math.cos(theta);
        point.lat = z * Math.sin(theta);

        return point;
    }
};


//////////////////下面的保持不动///////////////////////////////////
var scripts = document.getElementsByTagName("script");
var JS__FILE__ = scripts[scripts.length - 1].getAttribute("src");  //获得当前js文件路径
bmapcfg.home = JS__FILE__.substr(0, JS__FILE__.lastIndexOf("/") + 1); //地图API主目录
(function () {
    window.BMap_loadScriptTime = (new Date).getTime();
    //加载地图API主文件
    document.write('<script type="text/javascript" src="' + bmapcfg.home + 'bmap_offline_api_v3.0_min.js"></script>');
    //加载扩展函数
    document.write('<script type="text/javascript" src="' + bmapcfg.home + 'map_plus.js"></script>');
    //加载城市坐标
    document.write('<script type="text/javascript" src="' + bmapcfg.home + 'map_city.js"></script>');
})();
///////////////////////////////////////////////////////////////////
