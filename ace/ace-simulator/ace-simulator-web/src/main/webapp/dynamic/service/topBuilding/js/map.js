var longitude = null;
var latitude = null;
$(function(){
    var locaUrl = window.location.href;
    var url = window.location.href.substring(locaUrl.indexOf("?")+1);
    var paramArr = url.split("&");
    for(var i=0;i < paramArr.length;i++){
        num=paramArr[i].indexOf("=");
        if(num>0){
            name=paramArr[i].substring(0,num);
            value=paramArr[i].substr(num+1);
            if(name == "longitude"){
                longitude = value;
            }
            if(name == "latitude"){
                latitude = value;
            }
        }
    }
    if(longitude){
        var map = new qq.maps.Map(document.getElementById("container"), {
            center: new qq.maps.LatLng(latitude, longitude),
            zoom: 16
        });
        var marker = new qq.maps.Marker({
            //设置Marker的位置坐标
            position: new qq.maps.LatLng(latitude, longitude),
            //设置Marker被添加到Map上时的动画效果为落下
            animation: qq.maps.MarkerAnimation.DOWN,
            //设置Marker被添加到Map上时的动画效果为反复弹跳
            //animation:qq.maps.MarkerAnimation.BOUNCE
            //设置Marker被添加到Map上时的动画效果为从天而降
            animation: qq.maps.MarkerAnimation.DROP,
            //设置Marker被添加到Map上时的动画效果为升起
            //animation:qq.maps.MarkerAnimation.UP
            //设置显示Marker的地图
            map: map,
            //设置Marker可拖动
            draggable: true,
            //Marker的覆盖内容
            decoration: new qq.maps.MarkerDecoration("<font style='color:#fff;font-size:10px'>标记</font>"),
            //自定义Marker图标的阴影
            // shadow: new qq.maps.MarkerImage("https://open.map.qq.com/doc/img/nilb.png"),
            //设置Marker标题，鼠标划过Marker时显示
            title: '建筑物位置',
            //设置Marker的可见性，为true时可见,false时不可见
            visible: true
        });
    }
});