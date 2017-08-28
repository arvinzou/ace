jQuery(function($) {
	launchExample();
	$('#btn-search').on('click', function() {
		$('#fm-search').ajaxForm({
			beforeSubmit : function(formData, jqForm, options) {
				var params = {};
				$.each(formData, function(n, obj) {
					params[obj.name] = obj.value;
				});
				$.extend(params, {
					time : new Date()
				});
				
				chart1(params);
				return false;
			}
		});
	});
	
});
function initData() {
	var date =new Date().getFullYear();
	var params = {};
	params.year=date;
	chart1(params);
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
function chart1(params) {
	$.ajax({
		type : "post",
		data:params,
		url : contextPath + '/memberPayInfo/selectAnaysePayMentByMonth.do',
		success : function(rst) {
			$.each(rst, function(key, o) {
				//option1.legend.data.push(o.name);
				option1.series[0].data[key]=o.value;
			});
			
			initMyChar1();
		}
	});
	
}
