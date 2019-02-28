$(function(){
	init();
});
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