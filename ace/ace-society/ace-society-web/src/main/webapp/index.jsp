<%@page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="description" content="overview &amp; stats"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>${cfg.sys_name}</title>

</head>


<jsp:include page="/dynamic/common/header.jsp"/>


<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>

<div class="page-content-inner">

</div>

<div class="row widget-row">
    <div class="col-md-12">
        <div id="container" style="height: 920px">

        </div>
    </div>
</div>

<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>

<script id="tpl-portal-1" type="text/template">


    <div class="row widget-row">
        <div class="col-md-3">
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">会员个人</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-green icon-user"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.person}">\${data.person}</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">会员组织</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-red   fa fa-users"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">累计</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.org}">\${data.org}</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">线下活动</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-purple fa fa-heart"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">已审</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.activityAll}">\${data.activityAll}</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">线下活动</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-green  fa fa-heart"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">待审</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup"
                              data-value="\${data.activityAudit}">\${data.activityAudit}</span>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <div class="row widget-row">
        <div class="col-md-3">
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">文明随手拍</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-green fa fa-camera"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">已审</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.behavior}">\${data.behavior}</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">我有点子</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-red fa fa-envelope"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">已审</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.subjectIdea}">\${data.subjectIdea}</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">秀我直播</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-purple fa fa-microphone"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">已审</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.live}">\${data.live}</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 ">
                <h4 class="widget-thumb-heading">朋友圈</h4>
                <div class="widget-thumb-wrap">
                    <i class="widget-thumb-icon bg-blue  fa fa-photo"></i>
                    <div class="widget-thumb-body">
                        <span class="widget-thumb-subtitle">已审</span>
                        <span class="widget-thumb-body-stat" data-counter="counterup" data-value="\${data.circle}">\${data.circle}</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>


<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script>

    function App() {


    }

    Array.prototype.contains = function (needle) {
        for (i in this) {
            if (this[i] == needle) return true;
        }
        return false;
    }

    /*页面渲染*/
    function render(obj, data, tplId) {
        var tpl = document.getElementById(tplId).innerHTML;
        var html = juicer(tpl, {
            data: data,
        });
        $(obj).html(html);
    }

    window.onload = function () {
        var data = {reportId: "portal", userId: userProp.userId};
        var tplId = "tpl-portal-1";

        $.ajax({
            type: "post",
            url: contextPath + '/anslysis/query',
            data: data,
            success: function (rst) {
                var data = {};
                $.each(rst.value, function (i, o) {
                    data[o.id] = o.value;
                });

                render($(".page-content-inner"), data, tplId)
            }
        });
    }

</script>

<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=GA3nfXHbDcQaNEXmPBkaGdx2gvLMMBGy"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/AreaRestriction/1.2/src/AreaRestriction_min.js"></script>


<script type='text/javascript'>
    //提示：下面的代码用jquery，所以如果有不能运行的情况请引用后尝试
    //百度地图api container对应前端div名称 前端要引用2.0版本的百度地图api
    //需引用api.map.baidu.com/library/AreaRestriction/1.2/src/AreaRestriction_min.js

    var userlist = [];

    var map = new BMap.Map("container", {
        enableMapClick: false,
        minZoom: 10,
        maxZoom: 16,
    }); // 创建地图实例，禁止点击地图底图
    //设置样式
    map.setMapStyle({
        styleJson: [{ //不显示点信息
            "featureType": "poi",
            "elementType": "all",
            "stylers": {
                "color": "#ffffff",
                "visibility": "off"
            }
        }]
    });

//    map.enableDragging(); //禁止拖动
    map.disableDoubleClickZoom(); //禁止双击缩放
//    map.enableScrollWheelZoom(true);
    var blist = [];
    var headimg="data:image/png;base64,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"
    var districtLoading = 0;

    function getBoundary() {
        addDistrict("湖南常德市");
    }

    /**
     * 添加行政区划
     * @param {} districtName 行政区划名
     * @returns  无返回值
     */
    function addDistrict(districtName) {
        //使用计数器来控制加载过程
        districtLoading++;
        var bdary = new BMap.Boundary();
        bdary.get(districtName, function(rs) { //获取行政区域
            var count = rs.boundaries.length; //行政区域的点有多少个
            if(count === 0) {
                alert('未能获取当前输入行政区域');
                return;
            }
            for(var i = 0; i < count; i++) {
                blist.push({
                    points: rs.boundaries[i],
                    name: districtName
                });
            };
            //加载完成区域点后计数器-1
            districtLoading--;
            if(districtLoading == 0) {
                //全加载完成后画端点
                drawBoundary();
            }
        });
    }

    /**
     * 各种鼠标事件绑定
     */
    function click(evt) {
        alert(evt.target.name);
    }

    function mouseover(evt) {
        evt.target.label.setZIndex(99);
        evt.target.label.setPosition(evt.point);
        evt.target.label.show();
    }

    function mousemove(evt) {
        evt.target.label.setPosition(evt.point);
    }

    function mouseout(evt) {
        evt.target.label.hide();
    }

    function drawBoundary() {
        //包含所有区域的点数组
        var pointArray = [];

        /*画遮蔽层的相关方法
         *思路: 首先在中国地图最外画一圈，圈住理论上所有的中国领土，然后再将每个闭合区域合并进来，并全部连到西北角。
         *      这样就做出了一个经过多次西北角的闭合多边形*/
        //定义中国东南西北端点，作为第一层
        var pNW = {
            lat: 59.0,
            lng: 73.0
        }
        var pNE = {
            lat: 59.0,
            lng: 136.0
        }
        var pSE = {
            lat: 3.0,
            lng: 136.0
        }
        var pSW = {
            lat: 3.0,
            lng: 73.0
        }
        //向数组中添加一次闭合多边形，并将西北角再加一次作为之后画闭合区域的起点
        var pArray = [];
        pArray.push(pNW);
        pArray.push(pSW);
        pArray.push(pSE);
        pArray.push(pNE);
        pArray.push(pNW);
        //循环添加各闭合区域
        for(var i = 0; i < blist.length; i++) {
            //添加显示用标签层
            var label = new BMap.Label(blist[i].name, {
                offset: new BMap.Size(20, -10)
            });
            label.hide();
            map.addOverlay(label);

            //添加多边形层并显示
            var ply = new BMap.Polygon(blist[i].points, {
                strokeWeight: 5,
                strokeColor: "#FF0000",
                fillOpacity: 0.001,
                fillColor: " #FFFFFF"
            }); //建立多边形覆盖物
            ply.name = blist[i].name;
            ply.label = label;
            ply.addEventListener("click", click);
            ply.addEventListener("mouseover", mouseover);
            ply.addEventListener("mouseout", mouseout);
            ply.addEventListener("mousemove", mousemove);
            map.addOverlay(ply);

            //添加名称标签层
            var centerlabel = new BMap.Label(blist[i].name, {
                offset: new BMap.Size(0, 0)
            });
            centerlabel.setPosition(ply.getBounds().getCenter());
            map.addOverlay(centerlabel);

            //将点增加到视野范围内
            var path = ply.getPath();
            pointArray = pointArray.concat(path);
            //将闭合区域加到遮蔽层上，每次添加完后要再加一次西北角作为下次添加的起点和最后一次的终点
            pArray = pArray.concat(path);
            pArray.push(pArray[0]);
        }

        //限定显示区域，需要引用api库
        var boundply = new BMap.Polygon(pointArray);
        BMapLib.AreaRestriction.setBounds(map, boundply.getBounds());
        map.setViewport(pointArray); //调整视野

        //添加遮蔽层
        var plyall = new BMap.Polygon(pArray, {
            strokeOpacity: 0.0000001,
            strokeColor: "#000000",
            strokeWeight: 0.00001,
            fillColor: "#ffffff",
            fillOpacity: 0.9
        }); //建立多边形覆盖物
        map.addOverlay(plyall);
    }

    function getUserInfo() {

        var url = portalPath+"/wxUser/getSysWxUsers.do"
        $.getJSON(url, null, function (result) {

            if(result.status==0){
                userlist=result.data;
                var convertor = new BMap.Convertor();
                var pointArr = [];
                for(var i=0;i<userlist.length;i++) {
                    var pt = new BMap.Point(userlist[i].longitude, userlist[i].latitude);
                    pointArr.push(pt);

                }

                convertor.translate(pointArr, 3, 5, translateCallback)
            }
        })
    }

    translateCallback = function(data) {
        if(data.status === 0) {
            for(var i = 0; i < data.points.length; i++) {
                var img=userlist[i].avatarUrl;
                if(!img){
                  img=headimg;
                }
                var myIcon = new BMap.Icon(img, new BMap.Size(50, 50));
                map.addOverlay(new BMap.Marker(data.points[i],{ icon: myIcon }));
            }
        }
    }


    setTimeout(function() {
        getBoundary();
        getUserInfo();
    }, 100);
</script>





<style type="text/css">
    .BMap_Marker>div {
        border-radius: 50% !important;
    }

    .BMap_Marker img {
        width: 100%;
        height: 100%;
        object-position: 50% 50%;
    }
</style>

</body>
</html>