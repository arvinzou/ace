var _colNames =['','','','','','','','','','','','','','','操作'];
var _colModel = function() {
	return [
	{name : 'unionid',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"},editrules : {required : true}},{name : 'openid',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"},editrules : {required : true}},{name : 'appid',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"},editrules : {required : true}},{name : 'nickname',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"},editrules : {required : true}},{name : 'sex',editable : true,width : 100,edittype : "select",renderer : function(value) {return rsd(value, "01");},editoptions : {value : odparse("01")},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"},editrules : {required : true}},{name : 'city',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},},{name : 'province',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},},{name : 'country',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},},{name : 'headimgurl',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},},{name : 'privilege',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},},{name : 'accessToken',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},},{name : 'refreshToken',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},},{name : 'scope',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},},{name : 'expiresIn',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},},
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