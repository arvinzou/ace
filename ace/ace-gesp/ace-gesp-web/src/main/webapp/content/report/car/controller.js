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
		data:{reportId:'carType'},
		success : function(rst) {
			$.each(rst.value, function(i,o) {
				//console.log(o);
				//option1.legend.data.push(o.name);
				option1.series[0].data.push(o);
				
			});
			
			initMyChar1();
		}
	});
	
}

function chart2() {
	$.ajax({
		type : "post",
		url : contextPath + '/anslysis/query.do',
		data:{reportId:'carAge'},
		success : function(rst) {
			$.each(rst.value[0], function(name,value) {
				option2.xAxis[0].data.push(name);
				option2.series[0].data.push(value);
			});
			option2.series[0].markPoint.data.push(rst.value[0]);
			initMyChar2();
		}
	});
	
}

function chart3() {
	$.ajax({
		type : "post",
		url : contextPath + '/anslysis/query.do',
		data:{reportId:'managerCarBrand'},
		success : function(rst) {
			$.each(rst.value, function(name,val) {
				option3.xAxis[0].data.push(val.name);
				option3.series[0].data.push(val.value);
			});
			option3.series[0].markPoint.data.push(rst.value);
			initMyChar3();
		}
	});
	
}

function chart4() {
	if ($('#tableCarTon').hasClass('dataTable')) {
		dttable = $('#tableCarTon').dataTable();
		dttable.fnClearTable(); // 清空一下table
		dttable.fnDestroy(); // 还原初始化了的datatable
	}
	$.ajax({
		type : "post",
		url : contextPath + '/anslysis/query.do',
		data:{reportId:'managerCarTon'},
		success : function(rst) {
			var html = new Array();
			var total = 0;
			$.each(rst.value, function(i,o) {
				option4.series[0].data.push(o);
				var va = o.value;
				html.push('<tr>');
				html.push('<td width="70px">'+ o.name +'</td>');
				html.push('<td width="70px">'+ va +'</td>');
				html.push('</tr>');
				total = total+va;
			});
			html.push('<tr>');
			html.push('<td width="70px">总计：</td>');
			html.push('<td width="70px">'+ total +'</td>');
			html.push('</tr>');
			$('#tableCarTon').find('tbody').empty();
			$('#tableCarTon').find('tbody').append(html.join(''));
			initMyChar4();
		}
	});
}

function chart5() {
	$.ajax({
		type : "post",
		url : contextPath + '/anslysis/query.do',
		data:{reportId:'managerCarIllegal'},
		success : function(rst) {
			$.each(rst.value, function(name,val) {
				option5.xAxis[0].data.push(val.name);
				option5.series[0].data.push(val.value);
				option5.series[1].data.push(val.tvalue);
			});
			//option5.series[0].markPoint.data.push(rst.value);
			initMyChar5();
		}
	});
	
}
