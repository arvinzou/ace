var _colNames =['主键','所属诉求','类型','标题','内容','媒体类型','媒体资源','提交时间','提交人openId','提交人经度','提交人维度','答复内容','答复时间','答复人openId','状态','入库日期','操作'];
var _colModel = function() {
	return [
	{name : 'id',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"},editrules : {required : true}},{name : 'appealId',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"},editrules : {required : true}},{name : 'category',editable : true,width : 100,edittype : "select",renderer : function(value) {return rsd(value, "01");},editoptions : {value : odparse("01")},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"},editrules : {required : true}},{name : 'title',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"},editrules : {required : true}},{name : 'content',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"},editrules : {required : true}},{name : 'mediType',editable : true,width : 100,edittype : "select",renderer : function(value) {return rsd(value, "01");},editoptions : {value : odparse("01")},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"},editrules : {required : true}},{name : 'mediUrl',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},},{name : 'submitTime',editable : true,width : 100,edittype : "datebox",dataoptions : {    formatter : function(date) {var y = date.getFullYear();var m = date.getMonth() + 1;var d = date.getDate();return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);},parser : function(s) {if (!s)return new Date();var ss = (s.split('-'));var y = parseInt(ss[0], 10);var m = parseInt(ss[1], 10);var d = parseInt(ss[2], 10);if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {return new Date(y, m - 1, d);} else {return new Date();}}},editoptions : {size : "20",maxlength : "50"},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"},editrules : {required : true}},{name : 'submitOpenId',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"},editrules : {required : true}},{name : 'latitude',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},},{name : 'longitude',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},},{name : 'answerContent',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},},{name : 'answerTime',editable : true,width : 100,edittype : "datebox",dataoptions : {    formatter : function(date) {var y = date.getFullYear();var m = date.getMonth() + 1;var d = date.getDate();return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);},parser : function(s) {if (!s)return new Date();var ss = (s.split('-'));var y = parseInt(ss[0], 10);var m = parseInt(ss[1], 10);var d = parseInt(ss[2], 10);if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {return new Date(y, m - 1, d);} else {return new Date();}}},editoptions : {size : "20",maxlength : "50"},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"},editrules : {required : true}},{name : 'answerOpenId',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"},editrules : {required : true}},{name : 'status',editable : true,width : 100,edittype : "checkbox",editoptions : {value : "1:0"},cellattr : function(rowId, val, rawObject, cm, rdata) {if (rawObject.status == '0') {return "style='background:red;color:#fff'";}if (rawObject.status == '1') {return "style='background:#FF9224;color:#000000'";}if (rawObject.status == '2') {return "style='background:green;color:#fff'";}if (rawObject.status == '3') {return "style='background:#F9F900;color:#000000'";}if (rawObject.status == '4') {return "style='background:#FF9224;color:#000000'";}},unformat : aceSwitch,renderer : function(value) {var rst = "";switch (value) {case '1' :rst = "ON";break;case '0' :rst = "OFF";break;default :rst = "N/A";}return rst;},},{name : 'createDate',hidden : true,editable : false,width : 100},
	 {
                name: 'opt',
                width: 100,
                hidden:false,
                editable: false,
                sortable:false,
                renderer:function(value, cur){
                    return renderBtn(cur);
                }
            }
	];
}
function aceSwitch(cellvalue, options, cell) {
	console.log('aceSwitch');
	setTimeout(function() {
		$(cell).find('input[type=checkbox]').addClass(
				'ace ace-switch ace-switch-5').after(
				'<span class="lbl"></span>');
	}, 0);
}
// enable datepicker
function pickDate(cellvalue, options, cell) {
	setTimeout(function() {
		$(cell).find('input[type=text]').datepicker({
			format : 'yyyy-mm-dd',
			autoclose : true
		});
	}, 0);
}
function renderBtn(cur) {
	var id = $.jgrid.getAccessor(cur, 'id');
	var title = $.jgrid.getAccessor(cur, 'name');
	var html = [];
	html.push('<a target="_blank" href="');
	html.push('javascript:preview(\'' + id + '\',\'' + title + '\')');
	html.push('"');
	html.push('><span class="badge badge-info">查看</span></a>');
	return html.join(' ');
}