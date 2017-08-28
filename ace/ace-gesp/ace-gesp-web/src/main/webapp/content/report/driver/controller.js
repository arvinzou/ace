function initData() {
	chart1();
	chart2();
	chart3();
	
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
function initMyChar3() {
	if (myChart3 && myChart3.dispose) {
		myChart3.dispose();
	}
	myChart3 = echarts.init(document.getElementById('ct3'), curTheme);
	window.onresize = myChart3.resize;
	myChart3.setOption(option3, true);
	myChart3.hideLoading();
}
function chart1() {
	$.ajax({
		type : "post",
		url : contextPath + '/anslysis/query.do',
		data:{reportId:'driverAge'},
		success : function(rst) {
			$.each(rst.value[0], function(name,value) {
				option1.xAxis[0].data.push(name);
				option1.series[0].data.push(value);
			});
			//option1.series[0].markPoint.data.push(rst.value[0]);
			initMyChar1();
		}
	});
	
}

function chart2() {
	$.ajax({
		type : "post",
		url : contextPath + '/anslysis/query.do',
		data:{reportId:'driverDriYear'},
		success : function(rst) {
			$.each(rst.value[0], function(name,value) {
				option2.xAxis[0].data.push(name);
				option2.series[0].data.push(value);
			});
			//option2.series[0].markPoint.data.push(rst.value[0]);
			initMyChar2();
		}
	});
	
}

function chart3() {
	$.ajax({
		type : "post",
		url : contextPath + '/anslysis/query.do',
		data:{reportId:'driverDriArea'},
		success : function(rst) {
			$.each(rst.value, function(i,o) {

				option3.xAxis[0].data.push(o.name);
				option3.series[0].data.push(o.value);
				//option3.series[0].markPoint.data.push({name:o.name,value:o.value});
			});
			initMyChar3();
		}
	});
	
}