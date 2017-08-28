var _colNames = [ '编号','会员名称','营业执照号','法人代表','法人证件号', '初次入会时间', '会员有效截止', '会员等级', '创建人', '审核状态',
		'审核人', '审核时间', '审核备注', '操作' ];
var _colModel = function() {
	return [
			{
				name : 'memberCode',
				width : 120,
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
			}
			, {
				name : 'departmentName',
				width : 200,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'bussLicenseNo',
				width : 150,
				editable : true,
				hidden:true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'legalPersonName',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'legalPersonIdNo',
				width : 150,
				editable : true,
				hidden:true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'joinDate',
				width : 100,
				hidden:true,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				renderer : function(value) {
					return value.substring(0, 10);
				}
			}, {
				name : 'endDate',
				width : 100,
				hidden:true,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				renderer : function(value) {
					return value.substring(0, 10);
				}
			}, {
				name : 'memberLevel',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				renderer : function(value, cur) {
					return $.jgrid.getAccessor(cur, 'memberLevelName');
				}
			}, {
				name : 'createUserName',
				width : 100,
				editable : true,
				hidden:true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'status',
				width : 100,
				editable : true,
				hidden:true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				renderer : function(value) {
					return rsd(value, "67");
				}
			}, {
				name : 'auditUserName',
				width : 100,
				editable : true,
				hidden:true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'auditDate',
				width : 120,
				editable : true,
				hidden:true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'auditRemark',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			},
			{
				name : 'opt',
				index : 'opt',
				width : 80,
				renderer : function(value, cur) {
					
					return renderBtn(cur);
				}
			} ];
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
	var id=$.jgrid.getAccessor(cur, 'id');
	var status=$.jgrid.getAccessor(cur, 'status');
	var departmentName=$.jgrid.getAccessor(cur, 'departmentName');
	var memberCode=$.jgrid.getAccessor(cur, 'memberCode');
	var html = [];
	html.push('<div title="发送催费消息" style="float:left;cursor:pointer;" ');
	html.push('class="ui-pg-div ui-inline-edit" ');
	html.push(' onclick="showPressDia(\''+memberCode+'\',\''+status+'\',\''+departmentName+'\',\''+id+'\')" ');
	html.push('onmouseover="jQuery(this).addClass("ui-state-hover");" ');
	html.push('onmouseout="jQuery(this).removeClass("ui-state-hover")" ');
	html.push(' data-original-title="发送催费消息">');
	html.push('<span class="ui-icon ace-icon fa fa-plus"></span></div>');
	//html.push('&nbsp;&nbsp;<a target="_blank" href="');
	//html.push('javascript:lookMemberInfo(\''+id+'\',\''+status+'\',\''+departmentName+'\',\''+memberCode+'\')');
	//html.push('"');
	//html.push('><span class="badge badge-info">查看</span></a></div>');
	return html.join(' ');
}