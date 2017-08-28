var _colNames = [ '编号','企业编号','会员证号', '会员名称', '营业执照号','法人代表','法人证件号','申请入会时间', '会员有效截止', '会员等级', '创建人', '审核状态',
		'审核人', '审核时间', '审核备注','操作' ];
var _colModel = function() {
	return [
			{
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
			},{
				name : 'memberCode',
				width : 200,
				hidden : true
			},{
				name : 'memberNo',
				width : 120
			}, {
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
				hidden:true,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'legalPersonName',
				hidden:true,
				width : 80,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'legalPersonIdNo',
				hidden:true,
				width : 150,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'joinDate',
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
				name : 'memberLevel',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				renderer : function(value, cur) {
					if(cur){
						return $.jgrid.getAccessor(cur, 'memberLevelName');
					}
				}
			}, {
				name : 'createUserName',
				width : 100,
				hidden:true,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'status',
				width : 80,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				renderer : function(value) {
					if(value=='8'){
						value = '1';
					}
					return rsd(value, "67");
				}
			}, {
				name : 'auditUserName',
				width : 60,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'auditDate',
				width : 150,
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
			},
			{
				name : 'opt',
				index : 'opt',
				sortable : false,
				width : 80,
				renderer : function(value, cur) {
					
					return renderBtn(cur, deptId);
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

function renderBtn(cur, deptId) {
	var id=$.jgrid.getAccessor(cur, 'id');
	var status=$.jgrid.getAccessor(cur, 'status');
	var departmentName=$.jgrid.getAccessor(cur, 'departmentName');
	var memberCode=$.jgrid.getAccessor(cur, 'memberCode');
	var author=(!authorConfig.hasOwnProperty(contextPath + "/memberInfo/updateAudit.do")||status!='0');
	var html = [];
	html.push('<div title="审核" style="float:left;cursor:pointer;" ');
	html.push('class="ui-pg-div ui-inline-edit" ');
	/*if(!author){
		html.push(' onclick="showAuditDia(\''+id+'\',\''+status+'\',\''+departmentName+'\',\''+memberCode+'\',\''+deptId+')" ');
	}*/
	
	html.push('onmouseover="jQuery(this).addClass("ui-state-hover");" ');
	html.push('onmouseout="jQuery(this).removeClass("ui-state-hover")" ');
	html.push(' data-original-title="审核">');
	
	if(!author){
		//html.push('<span class="ui-icon ace-icon fa fa-check">待审</span>');
		html.push('<span class="badge badge-warn">待审</span>');
	}else{
		html.push('<span class="badge badge-success">已审</span>');
	}
	html.push('</div>');
	
	/*html.push('<button class="btn btn-info" onclick="showAuditDia(\''+id+'\',\''+status+'\',\''+departmentName+'\',\''+memberCode+'\')"');
	if(!authorConfig.hasOwnProperty(contextPath + "/memberInfo/updateAudit.do")||status!='0'){
		html.push(' disabled="true"');
	}
	html.push(' authority="false">');
	html.push(' 审核<i');
	html.push(' class="ace-icon fa fa-legal  align-middle bigger-125 icon-on-right"></i>');
	html.push('</button>');*/
	return html.join(' ');
}