var _colNames = [ '会员名称', '入会时间','会员证号','会员等级', '会费到期时间','联系人','联系人手机','营业执照号', '法人代表', '法人证件号', 
		  '创建人', '状态', '审核人', '审核时间', '审核备注', '操作','编号'  ];
var _colModel = function() {
	return [{
				name : 'departmentName',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50",
					readonly : true,
					style : 'width:175px;line-height: 25px;height: 25px;'
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
				},
				editrules : {
					required : true
				}
			},{
				name : 'joinDate',
				width : 60,
				editable : true,
				hidden : false,
				edittype : "datebox",
				editoptions : {
					style:'width:176px;line-height: 25px;height: 25px;'
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
				editrules : {
					required : false
				}
			},
			{
				name : 'memberNo',
				editable : true,
				width : 60,
				editoptions : {
					style : 'width:175px;line-height: 25px;height: 25px;'
				}
			}, {
				name : 'memberLevel',
				width : 60,
				editable : true,
				edittype : "combobox",
				editoptions : {
					style : 'width:175px;line-height: 25px;height: 25px;',
					editable : false
				},
				dataoptions : {
					url : contextPath + '/memberLevel/selectListByDeptId.do',
					method : 'get',
					valueField : 'code',
					textField : 'name',
					panelHeight : 'auto',
					required : false
				},
				renderer : function(value, cur) {
					return $.jgrid.getAccessor(cur, 'memberLevelName');
				}
			},
			{
				name : 'endDate',
				width : 60,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50",
					style:'width:176px;line-height: 25px;height: 25px;'
				},
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
				editrules : {
					required : false
				}
			},
			{
				name : 'contactName',
				editable : false,
				width : 50
			},
			{
				name : 'contactMobile',
				editable : false,
				width : 50
			},
			{
				name : 'bussLicenseNo',
				width : 100,
				hidden : true,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			},
			{
				name : 'legalPersonName',
				width : 60,
				hidden : true,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			},
			{
				name : 'legalPersonIdNo',
				width : 70,
				hidden : true,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}
			, {
				name : 'createUserName',
				hidden : true,
				width : 60,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'status',
				width : 50,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				renderer : function(value) {
					return rsd(value, "96");
				}
			}, {
				name : 'auditUserName',
				hidden : true,
				width : 60,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'auditDate',
				hidden : true,
				width : 90,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'auditRemark',
				width : 80,
				hidden : true,
				editable : false,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'opt',
				width : 50,
				hidden:false,
				sortable : false,
				renderer : function(value, cur) {

					return renderBtn(cur);
				}
			},{
				name : 'id',
				width : 100,
				editable : true,
				hidden : true,
				editoptions : {
					size : "20",
					maxlength : "50",
					readonly : true,
					style : 'width:175px;line-height: 25px;height: 25px;'
				},
				formoptions : {
					elmprefix : "",
					elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'></span>"
				},
				editrules : {
					required : true
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