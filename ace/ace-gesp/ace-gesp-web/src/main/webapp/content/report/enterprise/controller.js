function initData() {
	chart1();
	chart2();
	chart3();
	chart4();
	chart5();
	
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
function initMyChar4() {
	if (myChart4 && myChart4.dispose) {
		myChart4.dispose();
	}
	myChart4 = echarts.init(document.getElementById('ct4'), curTheme);
	window.onresize = myChart4.resize;
	myChart4.setOption(option4, true);
	myChart4.hideLoading();
}
function initMyChar5() {
	if (myChart5 && myChart5.dispose) {
		myChart5.dispose();
	}
	myChart5 = echarts.init(document.getElementById('ct5'), curTheme);
	window.onresize = myChart5.resize;
	myChart5.setOption(option5, true);
	myChart5.hideLoading();
}
function chart1() {
	$.ajax({
		type : "post",
		url : contextPath + '/anslysis/query.do',
		data:{reportId:'enterpriseArea'},
		success : function(rst) {
			$.each(rst.value, function(i,o) {
				var data={};
				//console.log(o);
				option1.xAxis[0].data.push(o.name);
				option1.series[0].data.push(o.memberCount);
				//option1.series[0].markPoint.data.push({name:o.name,value:o.deptCount});
				
				option1.series[1].data.push(o.deptCount);
				//option1.series[1].markPoint.data.push({name:o.name,value:o.deptCount});
				initMyChar1();
			});
			
			
		}
	});
}

function chart2() {
	$.ajax({
		type : "post",
		url : contextPath + '/anslysis/query.do',
		data:{reportId:'enterpriseEmpl'},
		success : function(rst) {
			$.each(rst.value[0], function(name,value) {
				option2.xAxis[0].data.push(name);
				option2.series[0].data.push(value);
			});
			option2.series[0].markPoint.data.push(rst.value[0]);
			$.each(rst.value[1], function(name,value) {
				option2.series[1].data.push(value);
			});
			//option2.series[1].markPoint.data.push(rst.value[1]);
			initMyChar2();
		}
	});
	
}
function chart3() {
	$.ajax({
		type : "post",
		url : contextPath + '/anslysis/query.do',
		data:{reportId:'enterpriseServiceScope'},
		success : function(rst) {
			$.each(rst.value, function(i,o) {
				
				//console.log(o);
				option3.xAxis[0].data.push(o.name);
				option3.series[0].data.push(o.value);
				//option3.series[0].markPoint.data.push({name:o.name,value:o.value});
				
				option3.series[1].data.push(o.value2);
				//option3.series[1].markPoint.data.push({name:o.name,value:o.value2});
				initMyChar3();
			});
		}
	});
	
}
function chart4() {
	$.ajax({
		type : "post",
		url : contextPath + '/anslysis/query.do',
		data:{reportId:'regCapital'},
		success : function(rst) {
			$.each(rst.value[0], function(name,value) {
				option4.xAxis[0].data.push(name);
				option4.series[0].data.push(value);
			});
			//option4.series[0].markPoint.data.push(rst.value[0]);
			$.each(rst.value[1], function(name,value) {
				option4.series[1].data.push(value);
			});
			//option4.series[1].markPoint.data.push(rst.value[1]);
			initMyChar4();
		}
	});
}
function chart5() {
	$.ajax({
		type : "post",
		url : contextPath + '/anslysis/query.do',
		data:{reportId:'enterpriseCar'},
		success : function(rst) {
			$.each(rst.value[0], function(name,value) {
				option5.xAxis[0].data.push(name);
				option5.series[0].data.push(value);
			});
			//option5.series[0].markPoint.data.push(rst.value[0]);
			$.each(rst.value[1], function(name,value) {
				option5.series[1].data.push(value);
			});
			//option5.series[1].markPoint.data.push(rst.value[1]);
			initMyChar5();
		}
	});
	
}