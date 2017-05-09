jQuery(function($) {
	launchExample();
});
function initData() {
	chart1();
	//chart2();
	//chart3();
}
function initMyChar1() {
	if (myChart1 && myChart1.dispose) {
		myChart1.dispose();
	}
	myChart1 = echarts.init(document.getElementById('ct1'), curTheme);
	window.onresize = myChart1.resize;
	myChart1.setOption(option1, true);
	myChart1.hideLoading();
}

function chart2() {
	$
			.ajax({
				type : "post",
				url : contextPath + '/homeChart/selectWorkData.do',
				success : function(rst) {
					var html = new Array();
					var k = 0;
					for ( var i in rst.value) {
						var o = rst.value[i];
						k++;

						html.push('<tr>');
						html.push('<td width="60px">');
						html.push('<span class="badge badge-info">' + (k)
								+ '</span> ');

						html.push('</td>');
						html.push('<td align="left">')
						html.push(o.name);
						html.push('</td>');
						html.push('<td>');
						html.push('<span class="badge badge-danger">');
						html.push(o.value);
						html.push('</span>  ');
						html.push('</td>');
						html.push('</tr>');
					}
					$('#work-list-grid').html(html.join(''));

				}
			});
}

function chart1() {
	$.ajax({
		type : "post",
		url : contextPath + '/chart/chart1.do',
		data : {
			meetingId : meetingId,
			topicId : topicId,
			normId : normId
		},
		success : function(rst) {
			option1.xAxis[0].data = rst.dataX;
			option1.series[0].data = rst.dataY;
			//option1.series[1].data = rst.index;
			option1.title.text=rst.name;
			option1.legend.data[0]=rst.name;
			option1.series[0].name=rst.name;

			initMyChar1();
		}
	});

}

function chart3() {
	$.ajax({
		type : "post",
		url : contextPath + '/anslysis/query.do',
		data : {
			reportId : "portal"
		},
		success : function(rst) {
			$.each(rst.value, function(i, o) {
				$('#' + o.id).html(o.value);
			});
		}
	});
}

window.onresize = function() {
	autosize()
}
function autosize() {
	var h = window.innerHeight;
	var w = window.innerWidth;
	var charh = 250;
	var charw = parseInt($(".page-content").width() / 2) - 40;
	charh = parseInt(charw * 0.5);
	$('#ct1').css("height", charh);
	$('#ct1').css("width", charw);
	$('#ct2').css("height", charh);
	$('#ct2').css("width", charw);
	if (myChart1) {
		myChart1.resize();
	}

}
