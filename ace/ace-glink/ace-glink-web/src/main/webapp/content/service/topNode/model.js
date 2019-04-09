var _colNames =['主键','节点编号','节点名称','节点描述','详细地址','经度','纬度','IPV4地址','IPV6地址','端口号','分辨率-宽','分辨率-高','mac地址','控制器数量','建筑物ID','备注','状态 ','创建人编号','创建人姓名','创建日期','更新人编号','更新人名称','更新日期','操作'];
var _colModel = function () {
return [
{name : 'id',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;'>*</span>"},editrules : {required : true}},{name : 'code',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;'>*</span>"},editrules : {required : true}},{name : 'name',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;'>*</span>"},editrules : {required : true}},{name : 'depict',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},},{name : 'address',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;'>*</span>"},editrules : {required : true}},{name : 'longitude',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},},{name : 'latitude',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},},{name : 'ipv4',editable : true,width : 100,edittype : "select",renderer : function(value) {return rsd(value, "01");},editoptions : {value : odparse("01")},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;'>*</span>"},editrules : {required : true}},{name : 'ipv6',editable : true,width : 100,edittype : "select",renderer : function(value) {return rsd(value, "01");},editoptions : {value : odparse("01")},},{name : 'port',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;'>*</span>"},editrules : {required : true}},{name : 'resolutionWidth',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;'>*</span>"},editrules : {required : true}},{name : 'resolutionHeight',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;'>*</span>"},editrules : {required : true}},{name : 'macAddr',editable : true,width : 100,edittype : "select",renderer : function(value) {return rsd(value, "01");},editoptions : {value : odparse("01")},},{name : 'ctrlNum',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},},{name : 'buildingId',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},},{name : 'remark',editable : true,width : 100,editoptions : {size : "20",maxlength : "50"},},{name : 'status',editable : true,width : 100,edittype : "checkbox",editoptions : {value : "1:0"},cellattr : function(rowId, val, rawObject, cm, rdata) {if (rawObject.status == '0') {return "style='color:red;'";}if (rawObject.status == '1') {return "style='color:#FF9224;'";}if (rawObject.status == '2') {return "style='color:green;'";}if (rawObject.status == '3') {return "style='color:#F9F900;'";}if (rawObject.status == '4') {return "style='color:#FF9224;'";}},unformat : aceSwitch,renderer : function(value) {var rst = "";switch (value) {case '1' :rst = "ON";break;case '0' :rst = "OFF";break;default :rst = "N/A";}return rst;},formoptions : {elmprefix : "",elmsuffix : "<span style='color:red;'>*</span>"},editrules : {required : true}},{name : 'createUserId',hidden : true,editable : false,width : 100},{name : 'createUserName',hidden : true,editable : false,width : 100},{name : 'createDate',hidden : true,editable : false,width : 100},{name : 'lastModifyUserId',hidden : true,editable : false,width : 100},{name : 'lastModifyUserName',hidden : true,editable : false,width : 100},{name : 'lastModifyDate',hidden : true,editable : false,width : 100},
{
name: 'opt',
width: 100,
hidden: false,
editable: false,
sortable: false,
renderer: function (value, cur) {
return renderBtn(cur);
}
}
];
}
function aceSwitch(cellvalue, options, cell) {
console.log('aceSwitch');
setTimeout(function () {
$(cell).find('input[type=checkbox]').addClass(
'ace ace-switch ace-switch-5').after(
'<span class="lbl"></span>');
}, 0);
}
// enable datepicker
function pickDate(cellvalue, options, cell) {
setTimeout(function () {
$(cell).find('input[type=text]').datepicker({
format: 'yyyy-mm-dd',
autoclose: true
});
}, 0);
}
function renderBtn(cur) {
var rowid = $.jgrid.getAccessor(cur, 'id');
var title = $.jgrid.getAccessor(cur, 'name');

var opt = [];
if (authorConfig.hasOwnProperty(cfg.grid_edit_data_url)) {
opt.push('<a href="javascript:edit(\'' + rowid + '\')">编辑</a> ');
}
if (authorConfig.hasOwnProperty(cfg.grid_delete_data_url)) {
opt.push('<a href="javascript:del(\'' + rowid + '\')">删除</a>  ');
}

opt.push('<a href="#" data-toggle="modal" data-id="' + rowid + '" data-title="' + title + '" ' +
'data-target="#modal-preview">查看</a>');

return opt.join(' ');
}