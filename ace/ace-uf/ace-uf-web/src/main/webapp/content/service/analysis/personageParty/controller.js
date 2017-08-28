var myChart;
function initData() {
	chart();

}
function initMyChar() {
	if (myChart && myChart.dispose) {
		myChart.dispose();
	}
	myChart = echarts.init(document.getElementById('ct'), curTheme);
	window.onresize = myChart.resize;
	myChart.setOption(option, true);
	myChart.hideLoading();
}
function chart() {
	$.ajax({
		type : "post",
		url : contextPath + '/anslysis/query.do',
		data : {
			reportId : 'personageParty'
		},
		success : function(rst) {
			option.series[0].data = rst.value;
			$(rst.value).each(function(i, o) {
				option.legend.data.push(o.name);
			});
			initMyChar();
			grid(rst.value);
		}
	});
}

function grid(data) {
	var html = [];
	$(data).each(function(i, o) {
		html.push("<tr>");
		html.push("<td>");
		html.push(i + 1);
		html.push("</td>");
		html.push("<td>");
		html.push(o.name);
		html.push("</td>");
		html.push("<td>");
		html.push(o.value);
		html.push("</td>");
		html.push("</tr>");
	});
	$("#table").find("tbody").html(html.join(""));
}