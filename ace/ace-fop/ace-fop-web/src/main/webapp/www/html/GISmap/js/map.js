$(function(){
// 百度地图API功能
var map = new BMap.Map("l-map"); // 创建地图实例
var point = new BMap.Point(111.698790, 29.031250); // 创建点坐标
map.centerAndZoom(point, 15); // 初始化地图，设置中心点坐标和地图级别
map.enableScrollWheelZoom();
map.addControl(new BMap.NavigationControl()); //添加默认缩放平移控件

var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
		{"input" : "suggestId"
		,"location" : map
	});
var customLayer;

function addCustomLayer(keyword) {
	if(customLayer) {
		map.removeTileLayer(customLayer);
	}
	customLayer = new BMap.CustomLayer({
		geotableId: 30960,
		q: '', //检索关键字
		tags: '', //空格分隔的多字符串
		filter: '' //过滤条件,参考http://lbsyun.baidu.com/lbs-geosearch.htm#.search.nearby
	});
	map.addTileLayer(customLayer);
	customLayer.addEventListener('hotspotclick', callback);
}

function callback(e) //单击热点图层
{
	var customPoi = e.customPoi; //poi的默认字段
	var contentPoi = e.content; //poi的自定义字段
	var content = '<p style="width:280px;margin:0;line-height:20px;">地址：' + customPoi.address + '<br/>价格:' + contentPoi.dayprice + '元' + '</p>';
	var searchInfoWindow = new BMapLib.SearchInfoWindow(map, content, {
		title: customPoi.title, //标题
		width: 290, //宽度
		height: 40, //高度
		panel: "panel", //检索结果面板
		enableAutoPan: true, //自动平移
		enableSendToPhone: true, //是否显示发送到手机按钮
		searchTypes: [
			BMAPLIB_TAB_SEARCH, //周边检索
			BMAPLIB_TAB_TO_HERE, //到这里去
			BMAPLIB_TAB_FROM_HERE //从这里出发
		]
	});
	var point = new BMap.Point(customPoi.point.lng, customPoi.point.lat);
	searchInfoWindow.open(point);
}
//document.getElementById("open").onclick = function() {
//	addCustomLayer();
//};
//document.getElementById("open").click();
//document.getElementById("close").onclick = function() {
//	if(customLayer) {
//		map.removeTileLayer(customLayer);
//	}
//};
// 创建CityList对象，并放在citylist_container节点内
var myCl = new BMapLib.CityList({
	container: "citylist_container",
	map: map
});

// 给城市点击时，添加相关操作
myCl.addEventListener("cityclick", function(e) {
	// 修改当前城市显示
	document.getElementById("curCity").innerHTML = e.name;

	// 点击后隐藏城市列表
	document.getElementById("cityList").style.display = "none";
});
// 给“更换城市”链接添加点击操作
document.getElementById("curCityText").onclick = function() {
	var cl = document.getElementById("cityList");
	if(cl.style.display == "none") {
		cl.style.display = "";
	} else {
		cl.style.display = "none";
	}
};
// 给城市列表上的关闭按钮添加点击操作
document.getElementById("popup_close").onclick = function() {
	var cl = document.getElementById("cityList");
	if(cl.style.display == "none") {
		cl.style.display = "";
	} else {
		cl.style.display = "none";
	}
};

function G(id) {
		return document.getElementById(id);
	}
ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
	var str = "";
		var _value = e.fromitem.value;
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
		
		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
		G("searchResultPanel").innerHTML = str;
	});

	var myValue;
	ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
	var _value = e.item.value;
		myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
		
		setPlace();
	});

	function setPlace(){
		map.clearOverlays();    //清除地图上所有覆盖物
		function myFun(){
			var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
			map.centerAndZoom(pp, 18);
			map.addOverlay(new BMap.Marker(pp));    //添加标注
		}
		var local = new BMap.LocalSearch(map, { //智能搜索
		  onSearchComplete: myFun
		});
		local.search(myValue);
	}
});