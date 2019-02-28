var regType = null;
var lat = null;
var long = null;
$(function(){
	init();
    initUserData();
    getLocation();
});
function initUserData(){
    $.ajax({
        url: contextPath+ "/www/sign/getAcctInfo",
        type:"post",
        async:false,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data:{
        },
        success:function(result){
            if(result.status == 0) {
                regType = result.data.regType;
                var date = new Date();
                var year = date.getFullYear();
                var month = date.getMonth()+1;
                var day = date.getDate();
                if(month < 10){
                	month = "0"+month;
				}
				var hour = date.getHours();
				var minite = date.getMinutes();
				if(hour < 12){
					$("#amBtn").show();
					$("#pmBtn").hide();
					$("#nightBtn").hide();
                    $("#am").text(hour+":"+minite);
				}else if(hour >=12 && hour < 19){
                    $("#amBtn").hide();
                    $("#pmBtn").show();
                    $("#nightBtn").hide();
                    $("#pm").text(hour+":"+minite);
				}else{
                    $("#amBtn").hide();
                    $("#pmBtn").hide();
                    $("#nightBtn").show();
                    $("#night").text(hour+":"+minite);
				}
                result.data.currentDate = year+"-"+month+"-"+day;
                renderPage('userInfo', result.data, 'user-tpl');
            }else {
                if(result.info){
                    alert(result.info);
                }else{
                    alert(result.errorMessage);
                }
                return;
            }
        },
        error:function(){
            alert("系统服务内部异常！");
        }
    });
}
function init() {
	var center = new qq.maps.LatLng(29.047770,111.598520);
    var map = new qq.maps.Map(document.getElementById("mapBox"), {
        // 地图的中心地理坐标。
        center: center,
        zoom: 16
    });
    var marker = new qq.maps.Marker({
    	position: center,
    	map: map
	});
	var cirle = new qq.maps.Circle({
		map: map,
    	center: center,
    	radius: 100,
        strokeWeight:1
	});
}

function getLocation(){
    wx.getLocation({
        type: 'gcj02', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
        success: function (res) {
            var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
            var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。

            var center = new qq.maps.LatLng(latitude,longitude);
            var map = new qq.maps.Map(document.getElementById("mapBox"), {
                // 地图的中心地理坐标。
                center: new qq.maps.LatLng(29.047770,111.598520),
                zoom: 16
            });
            var marker = new qq.maps.Marker({
                position: center,
                map: map
            });
        }
    });
}

function renderPage(IDom, data, tempId) {
    var tpl = document.getElementById(tempId).innerHTML;
    var html = juicer(tpl, {
        data: data,
    });
    $("#" + IDom).html(html);
}



/**
 * 点击签到
 */
function record(){

}