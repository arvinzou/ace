var _colNames = [ 'ID','操作','编号', '会员名称','营业执照号','法人代表','法人证件号',  '缴费金额','收费项目', '会员等级','缴费时间','会员有效截止', '收费人员','备注', '审核状态',
		'审核人', '审核时间', '审核备注','查看' ];
var _colModel = function() {
	return [{
		name : 'id',
		width : 120,
		editable : true,
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
		}
	},
	{
		name : 'opt',
		index : 'opt',
		width : 100,
		
		sortable : false,
		renderer : function(value, cur) {
			
			return renderBtn(cur);
		}
	},
	{
				name : 'memberCode',
				width : 120,
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
			}
			, {
				name : 'departmentName',
				width : 300,
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
				hidden:true,
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
				name : 'payAmount',
				width : 100,
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
				name : 'memberLevelId',
				width : 100,
				editable : true,
				hidden:false,
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
				hidden: false,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'remark',
				width : 100,
				hidden:true
			}, {
				name : 'status',
				width : 100,
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
				hidden:false,
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'auditDate',
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
				name : 'auditRemark',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			} , {
				name : 'preview',
				width : 100,
				hidden:true,
				sortable : false,
				renderer : function(value, cur) {
					return renderView(cur) ;
				}
			}];
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

function renderBtnbk(cur) {
	var id=$.jgrid.getAccessor(cur, 'id');
	var memberCode=$.jgrid.getAccessor(cur, 'memberCode');
	var status=$.jgrid.getAccessor(cur, 'status');
	var departmentName=$.jgrid.getAccessor(cur, 'departmentName');
	var html = [];
	html.push('<button class="btn btn-info" onclick="showAuditDia(\''+memberCode+'\',\''+status+'\',\''+departmentName+'\',\''+id+'\')"');
	if(!authorConfig.hasOwnProperty(contextPath + "/memberPayInfo/updateAudit.do")||status!='0'){
		html.push(' disabled="true"');
	}
	html.push(' authority="false">');
	html.push(' 审核<i');
	html.push(' class="ace-icon fa fa-check  align-middle bigger-125 icon-on-right"></i>');
	html.push('</button>');
	return html.join(' ');
}
function renderBtn(cur) {
	var id=$.jgrid.getAccessor(cur, 'id');
	var status=$.jgrid.getAccessor(cur, 'status');
	var departmentName=$.jgrid.getAccessor(cur, 'departmentName');
	var memberCode=$.jgrid.getAccessor(cur, 'memberCode');
	var memberLevelName = $.jgrid.getAccessor(cur, 'memberLevelName');
	var html = [];
	var author=(authorConfig.hasOwnProperty(contextPath + "/memberPayInfo/updateAudit.do")&&status=='0');
	html.push('<div title="审核" style="float:left;cursor:pointer;" align="center" ');
	html.push('class="ui-pg-div ui-inline-edit" ');
	if(author){
		html.push(' onclick="showAuditDia(\''+memberCode+'\',\''+status+'\',\''+departmentName+'\',\''+id+'\',\''+memberLevelName+'\')" ');
	}
	html.push('onmouseover="jQuery(this).addClass("ui-state-hover");" ');
	html.push('onmouseout="jQuery(this).removeClass("ui-state-hover")" ');
	html.push(' data-original-title="审核">');
	if(author){
		html.push('<span class="ui-icon ace-icon fa fa-check"></span>');
		//html.push('<i class="fa fa-check-square-o fa-2" aria-hidden="true"></i>');
	}else{
		html.push('<span class="badge badge-success">已审</span>');
	}
	html.push('</div>');
	return html.join(' ');
}
function renderView(cur) {
	var id = $.jgrid.getAccessor(cur, 'id');
	var status = $.jgrid.getAccessor(cur, 'status');
	var departmentName = $.jgrid.getAccessor(cur, 'departmentName');
	var memberCode = $.jgrid.getAccessor(cur, 'memberCode');
	var html = [];

	html.push('<a target="_blank" href="');
	html.push('javascript:lookMemberInfo(\''+id+'\',\''+status+'\',\''+departmentName+'\',\''+memberCode+'\')');
	html.push('"');
	html.push('><span class="badge badge-info">查看</span></a>');
	return html.join(' ');
}