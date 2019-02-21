if (!window.innerHeight) {
	var height = 0;
	if (window.innerHeight) {
		height = window.innerHeight;
	} else if (document.documentElement
			&& document.documentElement.clientHeight) {
		height = document.documentElement.clientHeight;
	} else if (document.body && document.body.clientHeight) {
		height = document.body.clientHeight;
	}
	window.innerHeight = height;
}
if (!window.innerWidth) {
	var width = 0;
	if (document.documentElement && document.documentElement.clientWidth) {
		width = document.documentElement.clientWidth;
	} else if (document.body && document.body.clientWidth) {
		width = document.body.clientWidth;
	} else if (window.innerWidth) {
		width = window.innerWidth;
	}
	window.innerWidth = width;
}
jQuery(function($) {
	var areaLendth = document.getElementsByTagName('area').length;
	var bi = window.innerWidth / 322;
	for (var k = 0; k < areaLendth; k++) {
		var coords = document.getElementsByTagName('area')[k].getAttribute('coords');
		var arrCoords = coords.split(',');
		for (var i = 0; i < arrCoords.length; i++) {
			arrCoords[i] = parseInt(arrCoords[i] * bi);
		}
		document.getElementsByTagName('area')[k].setAttribute('coords',arrCoords.join(','))
	}
	$(function() {
		FastClick.attach(document.body);
	});
	$(document.body).pullToRefresh().on("pull-to-refresh", function() {
		setTimeout(function() {
			pages = pages + 1;
			if (pages >= total) {
				pages = 0;
			}
			location.href = "index-" + pages+".html";
		}, 100);
	});
});