var _colNames =['系统编号','系统名称','logo','图标样式','','创建日期','创建人编号','创建人姓名','最后修改日期','最后修改人编号','最后修改人姓名'];
var _colModel = function() {
	return [
	{name : 'id',width : 100,editable : true,editoptions : {size : "20",maxlength : "50"},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"},editrules : {required : true}},{name : 'name',width : 100,editable : true,editoptions : {size : "20",maxlength : "50"}},{name : 'logo',width : 100,editable : true,editoptions : {size : "20",maxlength : "50"}},{name : 'iconCls',width : 100,editable : true,editoptions : {size : "20",maxlength : "50"}},{name : 'colorCls',width : 100,editable : true,editoptions : {size : "20",maxlength : "50"}},{name : 'createDate',width : 100,editable : true,editoptions : {size : "20",maxlength : "50"},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"},editrules : {required : true}},{name : 'createUserId',width : 100,editable : true,editoptions : {size : "20",maxlength : "50"},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"},editrules : {required : true}},{name : 'createUserName',width : 100,editable : true,editoptions : {size : "20",maxlength : "50"},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"},editrules : {required : true}},{name : 'lastModifyDate',width : 100,editable : true,editoptions : {size : "20",maxlength : "50"}},{name : 'lastModifyUserId',width : 100,editable : true,editoptions : {size : "20",maxlength : "50"}},{name : 'lastModifyUserName',width : 100,editable : true,editoptions : {size : "20",maxlength : "50"}}
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