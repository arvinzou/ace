<%--
  Created by IntelliJ IDEA.
  User: HuaCai008
  Date: 2018/11/5
  Time: 9:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
    <style type="text/css">
        body, html {
            width: 100%;
            height: 100%;
            margin: 0;
            font-family: "微软雅黑";
        }

        #allmap {
            height: 500px;
            width: 100%;
        }

        #r-result {
            width: 100%;
            font-size: 14px;
        }
    </style>
    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=2.0&ak=cPY4B8MAYgPQYOuDKPTNvUin31DBPDCB"></script>
    <title>地图定位</title>
</head>
<jsp:include page="../../common/common.jsp"/>
<body>

<div id="allmap" style="width: 100%;height: 100%"></div>
<div id="r-result" style="display: none">
    名称：<input id="name" type="text" style="width:100px; margin-right:10px;"/>
    经度: <input id="longitude" type="text" style="width:100px; margin-right:10px;"/>
    纬度: <input id="latitude" type="text" style="width:100px; margin-right:10px;"/>
</div>

<jsp:include page="../../common/footer-1.jsp"/>
<jsp:include page="../../common/footer-2.jsp"/>
</body>
<script src="${pageContext.request.contextPath}/content/service/association/config.js?version=${cfg.version}"></script>
</html>
<script type="text/javascript">
    /**
     * 获取指定的URL参数值
     * 参数：paramName URL参数
     * 调用方法:getParam("name")
     * 返回值:tyler
     */
    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }

    function change(lat, lng) {
        var x_pi = 3.14159265358979324 * 3000.0 / 180.0;
        var x = parseFloat(lng);
        var y = parseFloat(lat);
        var z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
        var theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
        lng = z * Math.cos(theta) + 0.0065;
        lat = z * Math.sin(theta) + 0.006;

        return {latitude: lat, longitude: lng};
    }

    var did = getQueryString("did");

    $.ajax({
        type: "post",
        url: cfg.view_load_data_url,
        data: {id: did},
        beforeSend: function (XMLHttpRequest) {
        },
        success: function (rst, textStatus) {
            var obj = rst.value;
            //获取页面参数
            var txt = obj.fullName, mouseoverTxt = obj.fullName;
            //经度
            var longitude = obj.longitude;// getQueryString('longitude');
            //维度
            var latitude = obj.latitude;//getQueryString('latitude');
            console.log(txt + "|" + longitude + "|" + latitude);
            //
            var ary = change(latitude, longitude);
            latitude = ary.latitude;
            longitude = ary.longitude;

            console.log(txt + "|" + longitude + "|" + latitude);
            // 百度地图API功能
            var map = new BMap.Map("allmap");
            map.addControl(new BMap.NavigationControl());
            map.addControl(new BMap.ScaleControl());
            map.addControl(new BMap.OverviewMapControl());
            map.addControl(new BMap.MapTypeControl());
            //城市中心点
            //    var centerPoint = new BMap.Point(116.331398, 39.897445);
            //    map.centerAndZoom(centerPoint, 11);//
            map.centerAndZoom('常德', 13);      // 用城市名设置地图中心点
            map.enableScrollWheelZoom(true);
            // 复杂的自定义覆盖物
            function ComplexCustomOverlay(point, text, mouseoverText) {
                this._point = point;
                this._text = text;
                this._overText = mouseoverText;
            }

            ComplexCustomOverlay.prototype = new BMap.Overlay();
            ComplexCustomOverlay.prototype.initialize = function (map) {
                this._map = map;
                var div = this._div = document.createElement("div");
                div.style.position = "absolute";
                div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
                div.style.backgroundColor = "#EE5D5B";
                div.style.border = "0px solid #BC3B3A";
                div.style.color = "white";
                div.style.height = "25px";
                div.style.lineHeight = "25px";
                div.style.padding = "2px";
                div.style.whiteSpace = "nowrap";
                div.style.MozUserSelect = "none";
                div.style.fontSize = "14px"
                var span = this._span = document.createElement("span");
                div.appendChild(span);
                span.appendChild(document.createTextNode(this._text));
                var that = this;

                var arrow = this._arrow = document.createElement("div");
                arrow.style.background = "url(http://map.baidu.com/fwmap/upload/r/map/fwmap/static/house/images/label.png) no-repeat";
                arrow.style.position = "absolute";
                arrow.style.width = "11px";
                arrow.style.height = "10px";
                arrow.style.top = "22px";
                arrow.style.left = "10px";
                arrow.style.overflow = "hidden";
                div.appendChild(arrow);

                div.onmouseover = function () {
                    this.style.backgroundColor = "#6BADCA";
                    this.style.borderColor = "#0000ff";
                    this.getElementsByTagName("span")[0].innerHTML = that._overText;
                    arrow.style.backgroundPosition = "0px -20px";
                }

                div.onmouseout = function () {
                    this.style.backgroundColor = "#EE5D5B";
                    this.style.borderColor = "#BC3B3A";
                    this.getElementsByTagName("span")[0].innerHTML = that._text;
                    arrow.style.backgroundPosition = "0px 0px";
                }

                map.getPanes().labelPane.appendChild(div);

                return div;
            }
            ComplexCustomOverlay.prototype.draw = function () {
                var map = this._map;
                var pixel = map.pointToOverlayPixel(this._point);
                this._div.style.left = pixel.x - parseInt(this._arrow.style.left) + "px";
                this._div.style.top = pixel.y - 30 + "px";
            }

            //地图标注
            var myCompOverlay = new ComplexCustomOverlay(new BMap.Point(longitude, latitude), txt, mouseoverTxt);
            map.addOverlay(myCompOverlay);
        },
        error: function () {
            alert("加载错误！");
        }
    });
</script>
