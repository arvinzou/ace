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
function chart1() {
	$.ajax({
		type : "post",
		url : contextPath + '/anslysis/query.do',
		data : {
			reportId : 'personageCategory'
		},
		success : function(rst) {
			option1.series[0].data = rst.value;
			$(rst.value).each(function(i, o) {
				option1.legend.data.push(o.name);
			});
			initMyChar1();
		}
	});
}
function chart2() {
	$.ajax({
		type : "post",
		url : contextPath + '/anslysis/query.do',
		data : {
			reportId : 'personageArea'
		},
		success : function(rst) {
			$.each(rst.value, function(i,o) {
				option2.series[0].data.push(o.value);
				option2.xAxis[0].data.push(o.name);
			});
			initMyChar2();
		}
	});

}

function chart3() {
	$.ajax({
		type : "post",
		url : contextPath + '/anslysis/query.do',
		data : {
			reportId : 'personageAge'
		},
		success : function(rst) {
            var data=rst.value;
            var o=data[0];
			for(var key in o){
                    option3.legend.data.push(key);
                    option3.series[0].data.push({name:key,value:o[key]});
             }
			initMyChar3();
		}
	});
}
function chart4() {
	$.ajax({
		type : "post",
		url : contextPath + '/anslysis/query.do',
		data : {
			reportId : 'personageDept'
		},
		success : function(rst) {
			$.each(rst.value, function(i,o) {
				option4.series[0].data.push(o.value);
				option4.xAxis[0].data.push(o.name);
			});
			initMyChar4();
		}
	});

}