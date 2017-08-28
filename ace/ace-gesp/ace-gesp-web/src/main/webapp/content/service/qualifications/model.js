var _colNames = [ '编号', '会员名称','营业执照号','法人代表','法人证件号','状态','操作' ];
var _colModel = function() {
	return [
			{
				name : 'departmentId',
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
				},
				renderer : function(value, cur) {
					
					return value+renderBtn(cur,deId);
				}
			}, {
				name : 'bussLicenseNo',
				width : 150,
				editable : true,
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
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'status',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				renderer : function(value) {
					return rsd(value, "97");
				}
			} , {
				name : 'opt',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				renderer : function(value, cur) {
					
					return renderName(cur);
				}
			}];
}



function renderName(cur) {
	var id = $.jgrid.getAccessor(cur, 'id');
	var status = $.jgrid.getAccessor(cur, 'status');
	var departmentName = $.jgrid.getAccessor(cur, 'departmentName');
	var departmentId = $.jgrid.getAccessor(cur, 'departmentId');
	var html = [];
	html.push('<a target="_blank" href="');
	html.push('javascript:lookMemberInfo(\''+id+'\',\''+status+'\',\''+departmentName+'\',\''+departmentId+'\')');
	html.push('"');
	html.push('><span class="badge badge-info">查看</span></a>');
	return html.join(' ');
}

function renderBtn(cur,deId) {
	var departmentName=$.jgrid.getAccessor(cur, 'departmentName');
	var departmentId=$.jgrid.getAccessor(cur, 'departmentId');
	var html = [];
	html.push('<div title="审核" style="float:left;cursor:pointer;" ');
	html.push('class="ui-pg-div ui-inline-edit" ');
	html.push(' onclick="showAuditDia(\''+departmentName+'\',\''+departmentId+'\',\''+deId+'\')" ');
	html.push('onmouseover="jQuery(this).addClass("ui-state-hover");" ');
	html.push('onmouseout="jQuery(this).removeClass("ui-state-hover")" ');
	html.push(' data-original-title="审核">');
	html.push('<span class="ui-icon ace-icon fa fa-check"></span>');
	html.push('</div>');
	return html.join(' ');
}