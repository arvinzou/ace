<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>事故点标记</title>
</head>
<script charset="utf-8" src="https://map.qq.com/api/js?v=2.exp&key=ALFBZ-5Z2CJ-TK6F7-KVINX-AX5L7-UFBXL"></script>
<script type="text/javascript">
    var contextPath = '${pageContext.request.contextPath}';
    var portalPath = '${portalPath}';
    var version = '${cfg.version}';
    var fastdfs_server = '${cfg.fastdfs_server}';
    var activeSyId = '${SESSION_USERPROP_KEY.activeSyId}';
    var portalType = '${SESSION_USERPROP_KEY.cfg.portalType}';
    var default_page_list = [${cfg.default_page_list}];
</script>
<script>
    var id='${param.id}';
</script>
<body>
<style type="text/css">
        html,
        body {
            height: 100%;
            margin: 0px;
            padding: 0px
        }
        #container {
            width: 100%;
            height: 100%
        }
    </style>

        <div id="container"></div>


<script src="${portalPath}/content/common/assets/global/plugins/jquery.min.js?v=${cfg.version}"></script>
<script>
    var center=new qq.maps.LatLng(${param.latitude},${param.longitude});
    var map = new qq.maps.Map(document.getElementById("container"), {
                                center: center,
                                zoom: 18
                            });


                             //创建一个Marker
                                var marker = new qq.maps.Marker({
                                    //设置Marker的位置坐标
                                    position: center,
                                    //设置Marker被添加到Map上时的动画效果为落下
                                    animation: qq.maps.MarkerAnimation.DOWN,
                                    //设置Marker被添加到Map上时的动画效果为反复弹跳
                                    //animation:qq.maps.MarkerAnimation.BOUNCE
                                    //设置Marker被添加到Map上时的动画效果为从天而降
                                    animation:qq.maps.MarkerAnimation.DROP,
                                    //设置Marker被添加到Map上时的动画效果为升起
                                    //animation:qq.maps.MarkerAnimation.UP
                                    //设置显示Marker的地图
                                    map: map,
                                    //设置Marker可拖动
                                    draggable: true,
                                    //Marker的覆盖内容
                                    decoration: new qq.maps.MarkerDecoration("<font style='color:#fff;font-size:10px'></font>"),
                                    //自定义Marker图标为大头针样式
                                    icon: new qq.maps.MarkerImage("http://3gimg.qq.com/tencentMapTouch/lbs/img/nilt.png"),
                                    //自定义Marker图标的阴影
                                   // shadow: new qq.maps.MarkerImage("https://open.map.qq.com/doc/img/nilb.png"),
                                    //设置Marker标题，鼠标划过Marker时显示
                                    title: '',
                                    //设置Marker的可见性，为true时可见,false时不可见
                                    visible: true
                                });
</script>
</body>
</html>