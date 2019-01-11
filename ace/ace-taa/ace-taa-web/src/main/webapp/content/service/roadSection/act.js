function initPreview(id) {

            $.ajax({
                url: contextPath + '/roadGps/getList',
                type: "post",
                async: false,
                data: {
                    id: id
                },
                success: function(result) {

                    if(result.status == 0) {
                        if(result.value){
                            var o=result.value[0];
                            var map = new qq.maps.Map(document.getElementById("container"), {
                                center: new qq.maps.LatLng(o.latitude,o.longitude),
                                zoom: 16
                            });
                            var path = [];
                            //new qq.maps.LatLng(39.930, 116.425)
                            for(var i in result.value){
                                var e=result.value[i];
                                path.push(new qq.maps.LatLng(e.latitude,e.longitude));
                            }
                            var polygon = new qq.maps.Polyline({
                                map: map,
                                strokeColor: '#FF0000',
                                //strokeColor: new qq.maps.Color(0, 0, 0, 0.5),
                                //折线的样式
                                strokeDashStyle: 'solid',
                                //折线的宽度
                                strokeWeight: 6,
                                //折线末端线帽的样式
                                strokeLinecap: 'round',
                                path: path,
                                editable: true
                            });
                             //创建一个Marker
                                var marker = new qq.maps.Marker({
                                    //设置Marker的位置坐标
                                    position: new qq.maps.LatLng(o.latitude,o.longitude),
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
                                    decoration: new qq.maps.MarkerDecoration("<font style='color:#fff;font-size:10px'>标记</font>"),
                                    //自定义Marker图标为大头针样式
                                    icon: new qq.maps.MarkerImage("http://3gimg.qq.com/tencentMapTouch/lbs/img/nilt.png"),
                                    //自定义Marker图标的阴影
                                   // shadow: new qq.maps.MarkerImage("https://open.map.qq.com/doc/img/nilb.png"),
                                    //设置Marker标题，鼠标划过Marker时显示
                                    title: '测试',
                                    //设置Marker的可见性，为true时可见,false时不可见
                                    visible: true
                                });
                        }

                    } else {
                        alert(result.errorMessage);
                    }
                },
                error: function() {

                    alert("对不起出错了！");
                }
            });
}

jQuery(function($) {
    initPreview(id);
});