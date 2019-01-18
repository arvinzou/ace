var map=null;
function initPreview(params) {
	$.ajax({
		url: contextPath + '/traAcc/findTraAccList',
		type: "post",
		async: false,
		data: params,
		success: function(result) {

			if (result.status == 0) {
				if (result.rows) {
					var o = result.rows[0];
					map = new qq.maps.Map(document.getElementById("container"), {
						center: new qq.maps.LatLng(o.latitude, o.longitude),
						zoom: 11
					});

					for (var i in result.rows) {
						o = result.rows[i];
						new qq.maps.Marker({
                            //设置Marker的位置坐标
                            position: new qq.maps.LatLng(o.latitude, o.longitude),
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
                            decoration: new qq.maps.MarkerDecoration("<font style='color:#fff;font-size:10px'>"+o.roadManName+"</font>"),
                            //自定义Marker图标为大头针样式
                            icon: new qq.maps.MarkerImage("http://3gimg.qq.com/tencentMapTouch/lbs/img/nilt.png"),
                            //自定义Marker图标的阴影
                            // shadow: new qq.maps.MarkerImage("https://open.map.qq.com/doc/img/nilb.png"),
                            //设置Marker标题，鼠标划过Marker时显示
                            title: o.roadManName,
                            //设置Marker的可见性，为true时可见,false时不可见
                            visible: true
                        });
					}



				}
				//$("#modal-preview").modal("show");

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
	$(".RightDiv").css("height",(window.innerHeight-40)+"px");
	initPreview({});

	$("#FullScreen").click(function(){
           var ml= $("#TextViewPanel").css("margin-left");
           if(ml=='0px'){
                 $("#TextViewPanel").css("margin-left","-380px");
                 $("#FullScreen").css('background-position','-22px 0px');
				 $("#FullScreen").css('left','-1px');
           }else{
			    $("#TextViewPanel").css("margin-left","0px");
                $("#FullScreen").css('background-position','-44px 0px');
				$("#FullScreen").css('left','299px');
           }
    })
});


