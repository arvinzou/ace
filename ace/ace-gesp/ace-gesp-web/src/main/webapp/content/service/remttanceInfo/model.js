var _colNames = [ 'ID', '所属协会', '会员', '服务事项', '汇款银行名称', '汇款银行账号', '汇款人姓名',
		'汇款金额（元）', '汇款时间', '联系人手机', '凭证(汇款单附件)', '备注', '状态', '创建时间'];
var _colModel = function() {
	return [
			{
				name : 'id',
				width : 100,
				editable : true,
				hidden:true,
				editoptions : {
					size : "20",
					maxlength : "50",
					readOnly:true
				},
				editrules : {
					required : false
				}
			},
			{
				name : 'deptId',
				width : 200,
				editable : false,
				hidden:true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				},
				renderer : function(value, cur) {
					return $.jgrid.getAccessor(cur, 'deptName');
				}
			},
			{
				name : 'memberCode',
				width : 200,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				},
				renderer : function(value, cur) {
					return $.jgrid.getAccessor(cur, 'departmentName');
				}
			},
			{
				name : 'chargingItemId',
				width : 100,
				editable : true,
				editable : true,
				edittype : "combobox",
				dataoptions:{
					 url: contextPath +'/chargingItem/selectListByDeptId.do',
				        method: 'get',
				        valueField:'code',
				        textField:'name'
				},
				editoptions : {
					
					style:'line-height: 25px;height: 25px;'
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				},
				renderer : function(value, cur) {
					return $.jgrid.getAccessor(cur, 'chargingItemName');
				}
			},
			{
				name : 'bankName',
				width : 150,
				hidden:true,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				}
			},
			{
				name : 'bankAcc',
				width : 200,
				hidden:true,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				}
			},
			{
				name : 'name',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				}
			},
			{
				name : 'amount',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				}
			},
			{
				name : 'remDate',
				width : 100,
				editable : true,
				edittype : "datebox",
				editoptions : {
					style : 'width:175px;height:30px'
				},
				dataoptions : {
					formatter : function(date) {
						var y = date.getFullYear();
						var m = date.getMonth() + 1;
						var d = date.getDate();
						return y + '-' + (m < 10 ? ('0' + m) : m) + '-'
								+ (d < 10 ? ('0' + d) : d);
					},
					parser : function(s) {
						if (!s)
							return new Date();
						var ss = (s.split('-'));
						var y = parseInt(ss[0], 10);
						var m = parseInt(ss[1], 10);
						var d = parseInt(ss[2], 10);
						if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
							return new Date(y, m - 1, d);
						} else {
							return new Date();
						}
					}
				},
				renderer : function(value) {
					return value == null ? "" : value.substring(0, 10);
				},
			
			
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				}
			},
			{
				name : 'mobile',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				}
			},
			{
				name : 'fileAddr',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				},renderer : function(value, cur) {
					var html = [];
					html.push('<span title="汇款单附件" style="float:center;cursor:pointer;" ');
					html.push('class="ui-pg-div ui-inline-edit" ');
					html.push('  ');
					html.push('onmouseover="jQuery(this).addClass("ui-state-hover");" ');
					html.push('onmouseout="jQuery(this).removeClass("ui-state-hover")" ');
					html.push(' data-original-title="汇款单附件">');
					html.push('<span class="ui-icon ace-icon fa fa-arrow-circle-down"></span>');
					html.push('</span>');
					return "<a href='"+fastdfs_server+value+"' target='_bladk'>"+html.join(" ")+"</a>";
				}
			},
			{
				name : 'remark',
				width : 100,
				hidden:true,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			},
			{
				name : 'status',
				width : 100,
				hidden:true,
				sortable : false,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				}
			}, {
				name : 'createDate',
				width : 100,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50"
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
