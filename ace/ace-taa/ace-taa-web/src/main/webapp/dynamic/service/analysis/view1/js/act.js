var map = null;
var markers=[];
var params = {
    limit: 9999999
};
function findTraAccList(params) {
	$.ajax({
		url: contextPath + '/traAcc/findTraAccList',
		type: "post",
		async: false,
		data: params,
		success: function(result) {
			if(params.areaCode){
				getLatLongByAreaCode({areaCode:params.areaCode});
			}
			if (result.status == 0) {
				clearMarkers(markers);
				if (result.rows) {
					for (var i in result.rows) {
						var o=result.rows[i];
						var marker=new qq.maps.Marker({
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
							decoration: new qq.maps.MarkerDecoration("<font style='color:#fff;font-size:10px'>" + o.deadthToll +
								"</font>"),
							//自定义Marker图标为大头针样式
							icon: new qq.maps.MarkerImage("https://3gimg.qq.com/lightmap/api_v2/2/4/111/theme/default/imgs/markercluster/m1.png"),
							//自定义Marker图标的阴影
							// shadow: new qq.maps.MarkerImage("https://open.map.qq.com/doc/img/nilb.png"),
							//设置Marker标题，鼠标划过Marker时显示
							title: o.id,
							//设置Marker的可见性，为true时可见,false时不可见
							visible: true,
							o:o
							
						});
						//添加信息窗口
						var info = new qq.maps.InfoWindow({
							map: map
						});
						var inf=[];
						//获取标记的可拖动属性
						inf.push("<h5>");
						inf.push(o.address);
						inf.push("</h5>");
						inf.push("<div>");
						inf.push(o.accidentTime);
						inf.push("</div>");
						
						inf.push("<div>");
						inf.push(o.roadManName);
						inf.push("</div>");
						
						
						
						info.setPosition(marker.getPosition());
						qq.maps.event.addListener(marker, 'click', function() {
							//info.open();
							//info.setContent(inf.join(""));
							//info.setContent('<div style="text-align:center;white-space:nowrap;margin:10px;">'+marker.getTitle()+'</div>');
							//info.setPosition(marker.getPosition());
							alert(marker.getTitle());
						});
						markers.push(marker);
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

function clearMarkers(markers) {
      var marker;
      while (marker = markers.pop()) {
             marker.setMap(null);
      }
}
function getLatLongByAreaCode(data) {
	$.ajax({
		url: contextPath + '/traAcc/getLatLongByAreaCode',
		type: "post",
		async: false,
		data: data,
		success: function(result) {
			if (result.status == 0) {
				console.log(result);
				map.panTo(new qq.maps.LatLng(result.value.latitude, result.value.longitude));
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
	$(".RightDiv").css("height", (window.innerHeight - 40) + "px");
	$("#FullScreen").click(function() {
		var ml = $("#TextViewPanel").css("margin-left");
		if (ml == '0px') {
			$("#TextViewPanel").css("margin-left", "-380px");
			$("#FullScreen").css('background-position', '-22px 0px');
			$("#FullScreen").css('left', '-1px');
		} else {
			$("#TextViewPanel").css("margin-left", "0px");
			$("#FullScreen").css('background-position', '-44px 0px');
			$("#FullScreen").css('left', '299px');
		}
	})
	initMap();
	initForm();
	initEvents();
	getLatLongByAreaCode({areaCode:userProp.areaCode});
	findTraAccList({});
});

function initMap(){
	map = new qq.maps.Map(document.getElementById("Map"), {
		//center: new qq.maps.LatLng(result.value.latitude, result.value.longitude),
		zoom: 14,
		//mapTypeId: "coordinate",
		resizeKeepCenter: true,
		mapTypeControl: true,
		panControl: true,
		zoomControl: true,
		scaleControl: true,
		minZoom: 4,
		maxZoom: 18,
		//设置平移控件的位置
		panControlOptions: {
			//设置平移控件的位置为相对右方中间位置对齐.
			position: qq.maps.ControlPosition.RIGHT_CENTER
		},
		zoomControlOptions: {
			//设置缩放控件的位置为相对左方中间位置对齐.
			position: qq.maps.ControlPosition.RIGHT_CENTER,
			//设置缩放控件样式为仅包含放大缩小两个按钮
			style: qq.maps.ZoomControlStyle.SMALL
		}
		
	});
}
function initForm(){
	$('#fm').ajaxForm({
		beforeSubmit: function(formData, jqForm, options) {
			$.each(formData, function(n, obj) {
				params[obj.name] = obj.value;
			});
			console.log(params);
			findTraAccList(params);
			return false;
		}
	});
}
function setParams(key, value) {
    params[key] = value;
    findTraAccList(params);
}
function render(obj, data, tplId) {
    var tpl = document.getElementById(tplId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $(obj).html(html);
}

function initEvents() {
 

    $(".btn-group .btn").bind('click', function (event) {
        $(event.target).siblings().removeClass("active");
        console.log(event);
        $(event.target).addClass("active");
    });
    //道路级别
    var data = {};
    data.key = 'category';
    data.list = staticDictObject['170'];
    render($("#check-group-category"), data, "tpl-check-group");

    $("input[name=startDate]").datetimepicker({
        format: 'yyyy-mm-dd hh:ii:ss',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,//显示‘今日’按钮
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 'hour',  //Number, String. 默认值：0, 'hour'，日期时间选择器所能够提供的最精确的时间选择视图。
        clearBtn: true,//清除按钮
        forceParse: 0
    });
    $('input[name=startDate]').focus(function () {
        $(this).blur();//不可输入状态
    })


    $("input[name=endDate]").datetimepicker({
        format: 'yyyy-mm-dd hh:ii:ss',
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,//显示‘今日’按钮
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 'hour',  //Number, String. 默认值：0, 'hour'，日期时间选择器所能够提供的最精确的时间选择视图。
        clearBtn: true,//清除按钮
        forceParse: 0
    });
    $('input[name=endDate]').focus(function () {
        $(this).blur();//不可输入状态
    })

}