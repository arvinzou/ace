var _colNames =['主键','证件类型','证件号码','有效使用期限','所属个人ID','所属车辆ID','所属企业ID','证件照片1','证件照片2','证件照片3','证件照片4','备注','状态','创建人编号','创建人姓名','入库日期','最后更新人编号','最后更新人姓名','更新时间','操作'];
var _colModel = function() {
	return [
	{name : 'id',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"},editrules : {required : true}},{name : 'licenseType',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"},editrules : {required : true}},{name : 'licenseNo',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"},editrules : {required : true}},{name : 'expiryDate',editable : true,width : 100,edittype : "datebox",dataoptions : {    formatter : function(date) {var y = date.getFullYear();var m = date.getMonth() + 1;var d = date.getDate();return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);},parser : function(s) {if (!s)return new Date();var ss = (s.split('-'));var y = parseInt(ss[0], 10);var m = parseInt(ss[1], 10);var d = parseInt(ss[2], 10);if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {return new Date(y, m - 1, d);} else {return new Date();}}},editoptions : {size : "20",maxlength : "50"},},{name : 'personId',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},},{name : 'vehicleId',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},},{name : 'departmentId',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},},{name : 'licenseImg1',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"},editrules : {required : true}},{name : 'licenseImg2',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},},{name : 'licenseImg3',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},},{name : 'licenseImg4',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},},{name : 'remark',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},},{name : 'status',editable : true,width : 100,edittype : "checkbox",editoptions : {value : "1:0"},cellattr : function(rowId, val, rawObject, cm, rdata) {if (rawObject.status == '0') {return "style='background:red;color:#fff'";}if (rawObject.status == '1') {return "style='background:#FF9224;color:#000000'";}if (rawObject.status == '2') {return "style='background:green;color:#fff'";}if (rawObject.status == '3') {return "style='background:#F9F900;color:#000000'";}if (rawObject.status == '4') {return "style='background:#FF9224;color:#000000'";}},unformat : aceSwitch,renderer : function(value) {var rst = "";switch (value) {case '1' :rst = "ON";break;case '0' :rst = "OFF";break;default :rst = "N/A";}return rst;},},{name : 'createUserId',hidden : true,editable : false,width : 100},{name : 'createUserName',hidden : true,editable : false,width : 100},{name : 'createDate',hidden : true,editable : false,width : 100},{name : 'lastModifyUserId',hidden : true,editable : false,width : 100},{name : 'lastModifyUserName',hidden : true,editable : false,width : 100},{name : 'lastModifyDate',hidden : true,editable : false,width : 100},
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