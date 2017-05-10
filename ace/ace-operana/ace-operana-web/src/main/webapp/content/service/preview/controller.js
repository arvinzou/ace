jQuery(function($) {
	launchExample();
});
function initData() {
	chart1();
	chart2();
	chart3();
	chart4();
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
function initMyChar2() {
	if (myChart2 && myChart2.dispose) {
		myChart2.dispose();
	}
	myChart2 = echarts.init(document.getElementById('ct2'), curTheme);
	window.onresize = myChart2.resize;
	myChart2.setOption(option2, true);
	myChart2.hideLoading();
}

function chart2() {
	$.ajax({
		type : "post",
		url : contextPath + '/chart/chart2.do',
		data : {
			meetingId : meetingId,
			topicId : topicId,
			normId : normId
		},
		success : function(rst) {
			option2.xAxis[0].data = rst.dataX;
			option2.series[0].data = rst.dataY1;
			option2.series[1].data = rst.dataY2;
			if (rst.dataX.length <= 8) {
				// option1.series[0].barWidth=40;
			}
			initMyChar2();
		}
	});
}

function chart1() {
	$
			.ajax({
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
					option1.title.text = rst.name;
					//option1.legend.data[0] = rst.name;
					option1.series[0].name = rst.name;
					option1.series[0].markLine.data[0][1].xAxis = rst.xAxis;
					option1.series[0].markLine.data[0][0].yAxis = rst.yAxis;
					option1.series[0].markLine.data[0][0].value = rst.yAxis;
					option1.series[0].markLine.data[0][1].yAxis = rst.yAxis;
					if (rst.dataX.length <= 8) {
						option1.series[0].barWidth = 40;
					}
					var html = [];
					html
							.push("<tr style='background:#3D7878;text-align:center;font-weight:800;'>");
					html.push("<td colspan='" + (rst.dataX.length + 1)
							+ "' style='color:#FFFFFF'>");
					html.push(rst.name);
					html.push("</td>");
					html.push("<tr>");
					html
							.push("<td style='width:50px;color:red;font-weight:800'>");
					html.push('时间');
					html.push("</td>");
					$(rst.dataX).each(function(i, o) {
						html.push("<td style='color:red;font-weight:800'>");
						html.push(o);
						html.push("</td>");
					});
					html.push("</tr>");
					html.push("<tr>");
					html.push("<td style='width:100px;font-weight:800'>");
					html.push(rst.name);
					html.push("</td>");
					$(rst.dataY).each(function(i, o) {
						html.push("<td style='font-weight:800'>");
						html.push(o);
						html.push("</td>");
					});
					html.push("</tr>");
					/*html.push("<tr>");
					html.push("<td>");
					html.push('目标值');
					html.push("</td>");
					$(rst.index).each(function(i, o) {
						html.push("<td>");
						html.push(o);
						html.push("</td>");
					});
					html.push("</tr>");*/
					$("#grid1").html(html.join(""));
					initMyChar1();
				}
			});

}

function chart3() {
	$
			.ajax({
				type : "post",
				url : contextPath + '/chart/chart3.do',
				data : {
					meetingId : meetingId,
					topicId : topicId,
					normId : normId
				},
				success : function(rst) {
					var html = [];
					html
							.push("<tr style='background:#3D7878;text-align:center;font-weight:800;'>");
					html.push("<td colspan='" + (rst.item.length)
							+ "' style='color:#FFFFFF'>");
					html.push("不良现象明细");
					html.push("</td>");
					html
							.push("<tr style='background:#336666;text-align:center;font-weight:800;'>");
					$(rst.itemName)
							.each(
									function(i, o) {
										html
												.push("<td style='color:#FFFFFF;font-weight:800'>");
										html.push(o);
										html.push("</td>");
									});
					html.push("</tr>");
					$(rst.data)
							.each(
									function(i, e) {
										html.push("<tr>");
										$(rst.item)
												.each(
														function(i, o) {
															var background = "";
															if (o == rst.cwk) {
																background = "background:#006000;color:#FFFFFF;";
															}
															if (o == 'name') {
																background = "background:#FFFF6F";
															}
															html
																	.push("<td style='font-weight:800;"
																			+ background
																			+ "'>");
															html.push(e[o]);
															html.push("</td>");
														});
										html.push("</tr>");
									});
					$("#grid2").html(html.join(""));
				}
			});
}
function chart4() {
	$
			.ajax({
				type : "post",
				url : contextPath + '/chart/chart4.do',
				data : {
					meetingId : meetingId,
					topicId : topicId,
					normId : normId
				},
				success : function(rst) {
					var html = [];
					html
							.push("<tr style='background:#3D7878;text-align:center;font-weight:800;'>");
					html.push("<td colspan='" + (rst.item.length)
							+ "' style='color:#FFFFFF'>");
					html.push("TOP问题分析");
					html.push("</td>");
					html
							.push("<tr style='background:#336666;text-align:center;font-weight:800;'>");
					$(rst.itemName)
							.each(
									function(i, o) {
										html
												.push("<td style='color:#FFFFFF;font-weight:800'>");
										html.push(o);
										html.push("</td>");
									});
					html.push("</tr>");
					$(rst.data)
							.each(
									function(i, e) {
										html.push("<tr>");
										$(rst.item)
												.each(
														function(i, o) {
															var background = "";
															if (o == rst.cwk) {
																background = "background:#006000;color:#FFFFFF;";
															}
															if (o == 'probDiscri') {
																background = "background:#FFFF6F";
															}
															if (o == 'status') {
																if (e[o] == 'OPEN') {
																	background = "background:red";
																}
																if (e[o] == 'CLOSED') {
																	background = "background:green";
																}
																if (e[o] == 'NODOING') {
																	background = "background:#FFFF6F";
																}
															}
															html
																	.push("<td style='font-weight:800;"
																			+ background
																			+ "'>");
															html.push(e[o]);
															html.push("</td>");
														});
										html.push("</tr>");
									});
					$("#grid3").html(html.join(""));
				}
			});
}
window.onresize = function() {
	autosize()
}
function autosize() {
	var h = window.innerHeight;
	var w = window.innerWidth;
	var charh = 300;
	var charw = parseInt(w / 2) - 20;
	charh = parseInt(charw * 0.5);
	$('#ct1').css("height", charh);
	$('#ct1').css("width", charw);
	$('#ct2').css("height", charh);
	$('#ct2').css("width", charw);

	if (myChart1) {
		myChart1.resize();
	}

}
