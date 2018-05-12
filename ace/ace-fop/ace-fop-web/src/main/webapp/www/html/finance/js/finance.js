//var productName = '',
//	productMoney = '',
//	method = '',
//	year = '',
//	rate = '',
//	content = '';
//
//function submitForm() {
//
//	productName = $("input[name = 'productName']").val();
//	productMoney = $("input[name = 'productMoney']").val();
//	method = $("select[name = 'method']:selected").val();
//	year = $("input[name = 'year']").val();
//	rate = $("input[name = 'rate']").val();
//	content = $("textarea[name = 'content']").val();
//	var loadTime = new Date().Format("yyyy-MM-dd");
//
//	var appendText = '<div class="table_div">' +
//		'<table width="100%" border="0" cellspacing="0" cellpadding="0">' +
//		'<tr>' +
//		'<td colspan="3" class="project_title">[projectName]</td>' +
//		'<td>&nbsp;</td>' +
//		'<td><span class="income_title">预计年收益：</span><span class="income">[rate]</span></td>' +
//		'</tr>' +
//		'<tr>' +
//		'<td>企业名称：湖南华彩伟业有限公司</td>' +
//		'<td>企业性质：合资</td>' +
//		'<td>&nbsp;</td>' +
//		'<td>企业地址：常德市武陵区互联网产业园</td>' +
//		'<td>&nbsp;</td>' +
//		'</tr>' +
//		'<tr>' +
//		'<td>融资金额：[productMoney]</td>' +
//		'<td>融资年限：[year]</td>' +
//		'<td>&nbsp;</td>' +
//		'<td>&nbsp;</td>' +
//		'<td>&nbsp;</td>' +
//		'</tr>' +
//		'<tr>' +
//		'<td colspan="5">' +
//		'[content]' +
//		'</td>' +
//		'</tr>' +
//		'<tr>' +
//		'<td colspan="5">发布时间：[loadTime]</td>' +
//		'</tr>' +
//		'</table>' +
//		'</div>';
//	appendText = appendText.replace('[projectName]', productName);
//	appendText = appendText.replace('[rate]', rate);
//	appendText = appendText.replace('[productMoney]', productMoney);
//	appendText = appendText.replace('[year]', year);
//	appendText = appendText.replace('[content]', content);
//	appendText = appendText.replace('[loadTime]', loadTime);
//	$(".project_list").prepend(appendText);
//}
//
//Date.prototype.Format = function(fmt) {
//	var o = {
//		"M+": this.getMonth() + 1, //月份 
//		"d+": this.getDate(), //日 
//		"h+": this.getHours(), //小时 
//		"m+": this.getMinutes(), //分 
//		"s+": this.getSeconds(), //秒 
//		"q+": Math.floor((this.getMonth() + 3) / 3), //季度 
//		"S": this.getMilliseconds() //毫秒 
//	};
//	if(/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
//	for(var k in o)
//		if(new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
//	return fmt;
//}

$(function() {
	$('#myTab li:eq(0) a').tab('show');

	
	
});

