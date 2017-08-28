var _colNames = [ '编号','会员证号', '会员名称', '会员等级', '营业执照号', '法人代表', '法人证件号', '初次入会时间',
		'会费到期时间', '创建人', '审核状态', '审核人', '审核时间', '审核备注', '会员年费金额（元）','欠费状态','有效期倒计时（天）','查看', '操作' ];
var _colModel = function() {
	return [
			{
				name : 'memberCode',
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
			},  {
				name : 'memberNo',
				width : 120
			},{
				name : 'departmentName',
				width : 500,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'memberLevel',
				width : 160,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				renderer : function(value, cur) {
					return $.jgrid.getAccessor(cur, 'memberLevelName');
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
				width : 100,
				hidden:true,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'legalPersonIdNo',
				width : 150,
				hidden:true,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'joinDate',
				width : 160,
				editable : true,
				hidden:true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				renderer : function(value) {
					return value.substring(0, 10);
				}
			}, {
				name : 'endDate',
				width : 160,
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
				hidden:true,
				editable : true,
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
				hidden:true,
				width : 120,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'auditRemark',
				hidden:true,
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				}
			}, {
				name : 'payNum',
				width : 150,
				hidden : true
			}, {
				name : 'payStatus',
				width : 160,
				renderer : function(value) {
					var html = [];
					if(value=='0'){
						html.push('<span class="badge">缴费确认中</span>');
					}else if(value=='1'){
						html.push('<span class="badge badge-success">正常</span>');
					}else if(value=="2"){
						html.push('<span class="badge badge-warning">即将</span>');
					}else{
						html.push('<span class="badge badge-danger">欠费</span>');
					}
					
					return html.join(' ');
				}
			}, {
				name : 'leftDates',
				width : 160
			}, {
				name : 'preview',
				width : 60,
				hidden:true,
				sortable : false,
				editable : true,
				renderer : function(value, cur) {

					return renderView(cur);
				}
			}, {
				name : 'opt',
				index : 'opt',
				sortable : false,
				width : 100,
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

function renderBtnbk(cur) {
	var memberCode = $.jgrid.getAccessor(cur, 'memberCode');
	var status = $.jgrid.getAccessor(cur, 'status');
	var departmentName = $.jgrid.getAccessor(cur, 'departmentName');
	var memberLevel = $.jgrid.getAccessor(cur, 'memberLevel');
	var html = [];
	html
			.push('<button class="btn btn-info" onclick="showPayDia(\''
					+ memberCode + '\',\'' + status + '\',\'' + departmentName
					+ '\',\'' + memberLevel+ '\')"');
	if (!authorConfig.hasOwnProperty(contextPath
			+ "/memberPayInfo/insertMemberPayInfo.do")
			|| status != '1') {
		html.push(' disabled="true"');
	}
	html.push(' authority="false">');
	html.push(' 收款<i');
	html
			.push(' class="ace-icon fa fa-jpy  align-middle bigger-125 icon-on-right"></i>');
	html.push('</button>');
	return html.join(' ');
}
function renderBtn(cur) {
	var status = $.jgrid.getAccessor(cur, 'status');
	var departmentName = $.jgrid.getAccessor(cur, 'departmentName');
	var memberCode = $.jgrid.getAccessor(cur, 'memberCode');
	var memberLevel = $.jgrid.getAccessor(cur, 'memberLevel');
	var payStatus = $.jgrid.getAccessor(cur, 'payStatus');
	var html = [];
	var author = (authorConfig.hasOwnProperty(contextPath
			+ "/memberPayInfo/insertMemberPayInfo.do") && status == '1');
	html.push('<div title="收款" style="float:left;cursor:pointer;" ');
	html.push('class="ui-pg-div ui-inline-edit" ');
	if (author) {
		html.push(' onclick="showPayDia(\'' + memberCode + '\',\'' + status
				+ '\',\'' + departmentName + '\',\'' + memberLevel+ 
				'\',\'' + payStatus +'\')" ');
	}
	html.push('onmouseover="jQuery(this).addClass("ui-state-hover");" ');
	html.push('onmouseout="jQuery(this).removeClass("ui-state-hover")" ');
	html.push(' data-original-title="收款">');
	if (author) {
		html.push('<span class="ui-icon ace-icon fa fa-jpy"></span>');
	} else {
		html.push('<span class="badge badge-danger">待审</span>');
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
	html.push('javascript:lookMemberInfo(\'' + id + '\',\'' + status + '\',\''
			+ departmentName + '\',\'' + memberCode + '\')');
	html.push('"');
	html.push('><span class="badge badge-info">查看</span></a>');
	return html.join(' ');
}