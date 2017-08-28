var _colNames = [ 'ID', '编号', '会员名称', '收费项目', '汇款金额',
		'会员等级','缴费日期', '会员有效截止', '收费人员', '审核状态', '审核人', '审核时间', '审核备注','创建时间','操作' ];
var _colModel = function() {
	return [
			{
				name : 'id',
				width : 100,
				editable : true,
				hidden : true,
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
				name : 'memberCode',
				width : 100,
				hidden : true,
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
			}, {
				name : 'departmentName',
				width : 200,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'chargingItemName',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'payAmount',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'memberLevel',
				width : 100,
				editable : true,
				hidden : false,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				renderer : function(value, cur) {
					return $.jgrid.getAccessor(cur, 'memberLevelName');
				}
			},{
				name : 'createDate',
				width : 120,
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
				editable : true,
				hidden : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				renderer : function(value) {
					return value.substring(0, 10);
				}
			}, {
				name : 'createUserName',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'status',
				width : 100,
				hidden : false,
				editable : true,
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
				hidden : true,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'auditDate',
				width : 120,
				hidden : true,
				editable : true,
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
			} , {
				name : 'createDate',
				width : 100,
				editable : true,
				hidden : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				renderer : function(value) {
					return value.substring(0, 10);
				}
			} , {
				name : 'opt',
				hidden:true,
				width : 100,
				sortable : false,
				renderer : function(value,cur) {
					return renderBtn(cur);
				}
			}];
}
function renderBtn(cur) {
	var id = $.jgrid.getAccessor(cur, 'id');
	var status = $.jgrid.getAccessor(cur, 'status');
	var departmentName = $.jgrid.getAccessor(cur, 'departmentName');
	var memberCode = $.jgrid.getAccessor(cur, 'memberCode');
	var html = [];
	html.push('<a target="_blank" href="');
	html.push('javascript:lookMemberInfo(\'' + id + '\',\'' + status + '\',\''
			+ departmentName + '\',\'' + memberCode + '\')');
	html.push('"');
	html.push('><span class="badge badge-info">查看</span></a>');
	return html.join(' ');
}
